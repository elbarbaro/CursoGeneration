package com.barbaro.cursoandroid.db;

import com.barbaro.cursoandroid.models.Message;

import java.util.List;

public interface MessagesDAO {

    void insert(Message message);
    void insert(Message... messages);
    void delete(int id);
    void update(Message message);
    List<Message> list();
}
