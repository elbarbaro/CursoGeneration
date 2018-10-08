package com.barbaro.cursoandroid.home.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.barbaro.cursoandroid.R;
import com.barbaro.cursoandroid.models.Message;
import com.barbaro.cursoandroid.utils.Utils;

import java.util.List;

public class MessageAdapter extends ArrayAdapter {

    public MessageAdapter(@NonNull Context context, @NonNull List<Message> objects) {
        super(context, R.layout.item_message, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        }

        TextView txtUsername = convertView.findViewById(R.id.txtUserName);
        TextView txtBody = convertView.findViewById(R.id.txtBody);
        TextView txtTime = convertView.findViewById(R.id.txtTime);

        Message message = (Message)getItem(position);
        txtUsername.setText(message.getUsername());
        txtBody.setText(message.getBody());
        txtTime.setText(Utils.fromDateTimeFormat(message.getDate()));
        return convertView;
    }
}
