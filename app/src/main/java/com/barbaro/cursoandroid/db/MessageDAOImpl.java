package com.barbaro.cursoandroid.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.barbaro.cursoandroid.models.Message;
import com.barbaro.cursoandroid.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessagesDAO{

    private SQLiteDatabase sqLiteDatabase;
    private OnSQLDatabaseListener listener;

    public MessageDAOImpl(SQLiteDatabase sqLiteDatabase,
                          OnSQLDatabaseListener listener) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.listener = listener;
    }

    @Override
    public void insert(Message message) {
        ContentValues values = new ContentValues();
        values.put(MessageTable.ID_FIELD, message.getId());
        values.put(MessageTable.USER_NAME_FIELD, message.getUsername());
        values.put(MessageTable.BODY_FIELD, message.getBody());
        values.put(MessageTable.TIME_FIELD, Utils.fromDateTimeFormat2(message.getDate()));
        sqLiteDatabase.insert(MessageTable.TABLE_NAME, null, values);
    }

    @Override
    public void insert(Message... messages) {
        for(Message message: messages){
            insert(message);
        }
        listener.onMessage("Mensajes guardados");
    }

    @Override
    public void delete(int id) {
        sqLiteDatabase.delete(
                MessageTable.TABLE_NAME,
                MessageTable.ID_FIELD + "= ?",
                new String[] {String.valueOf(id)});
        listener.onMessage("Se elimino el mensaje");
    }

    @Override
    public void update(Message message) {
        ContentValues values = new ContentValues();
        values.put(MessageTable.BODY_FIELD, message.getBody());
        values.put(MessageTable.TIME_FIELD, Utils.fromDateTimeFormat2(message.getDate()));
        sqLiteDatabase.update(
                MessageTable.TABLE_NAME,
                values,
                MessageTable.ID_FIELD + "= ?",
                new String[] {String.valueOf(message.getId())});
        listener.onMessage("Se actualizo el mensaje");
    }

    @Override
    public List<Message> list() {
        List<Message> messages = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MessageTable.TABLE_NAME + ";", null);
        if(cursor.moveToFirst()){
            do{
                messages.add(new Message(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        Utils.fromDateTimeFormat(cursor.getString(3))
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        listener.onMessage("Se obtuvieron mensajes");
        return messages;
    }

    public void close(){
        if(sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
    }
}
