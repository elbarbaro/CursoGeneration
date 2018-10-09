package com.barbaro.cursoandroid;

import android.content.Context;

import com.barbaro.cursoandroid.db.DBHelper;
import com.barbaro.cursoandroid.db.MessageDAOImpl;
import com.barbaro.cursoandroid.db.MessagesDAO;
import com.barbaro.cursoandroid.db.OnSQLDatabaseListener;
import com.barbaro.cursoandroid.models.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MessageRepository {

    private Context context;
    private OnSQLDatabaseListener listener;
    private DBHelper dbHelper;

    public MessageRepository(Context context, OnSQLDatabaseListener listener) {
        this.context = context;
        this.listener = listener;
        this.dbHelper = new DBHelper(context);
    }

    public void generateMessages(){
        Message[] messages = new Message[10];
        for(int i = 0; i < 10; i++){
            messages[i] = new Message(i, "Usuario " + (i + 1), "Cuerpo de mensaje " + (i + 1), new Date());
        }
        setList(messages);
    }

    public List<Message> getRemoteMessages(){
        //TODO HACER IMPLEMENTACION DE DATOS REMOTOS
        return null;
    }

    public List<Message> getLocalMessages(){
        MessagesDAO dao = new MessageDAOImpl(dbHelper.getReadableDatabase(), listener);
        return dao.list();
    }

    public void setList(Message... messages){
        MessagesDAO dao = new MessageDAOImpl(dbHelper.getWritableDatabase(), listener);
        dao.insert(messages);
    }

    public void update(Message message){
        MessagesDAO dao = new MessageDAOImpl(dbHelper.getWritableDatabase(), listener);
        dao.update(message);
    }

    public void delete(int id){
        MessagesDAO dao = new MessageDAOImpl(dbHelper.getWritableDatabase(), listener);
        dao.delete(id);
    }
}
