package com.barbaro.cursoandroid.models;

import java.util.Date;

public class Message {

    private int id;
    private String username;
    private String body;
    private Date date;

    public Message(int id, String username, String body, Date date) {
        this.id = id;
        this.username = username;
        this.body = body;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }
}
