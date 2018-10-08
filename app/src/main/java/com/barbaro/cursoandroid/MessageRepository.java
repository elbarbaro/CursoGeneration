package com.barbaro.cursoandroid;

import com.barbaro.cursoandroid.models.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageRepository {

    public static List<Message> getMessages(){
        List<Message> messages = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            messages.add(new Message(i, "Usuario " + (i + 1), "Cuerpo de mensaje " + (i + 1), new Date()));
        }
        return messages;
    }

    public static List<Message> getRemoteMessages(){
        //TODO HACER IMPLEMENTACION DE DATOS REMOTOS
        return null;
    }
}
