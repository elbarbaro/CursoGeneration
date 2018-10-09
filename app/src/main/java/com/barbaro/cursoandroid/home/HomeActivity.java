package com.barbaro.cursoandroid.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.barbaro.cursoandroid.MessageRepository;
import com.barbaro.cursoandroid.R;
import com.barbaro.cursoandroid.home.adapters.MessageAdapter;
import com.barbaro.cursoandroid.profile.PhotoActivity;

public class HomeActivity extends AppCompatActivity {

    private ListView listMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listMessages = findViewById(R.id.listMessages);
        listMessages.setAdapter(fillMessages());
    }

    private ListAdapter fillMessages() {
        return new MessageAdapter(this, MessageRepository.getMessages());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.create:
                takePhoto();
                return true;
            case R.id.update:
                showMessage("Actualizar");
                return true;
            case R.id.delete:
                showMessage("Eliminar");
                return true;
            default:
                return false;
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(HomeActivity.this, PhotoActivity.class);
        startActivity(intent);
    }

    private void showMessage(String action) {
        Toast.makeText(this, action, Toast.LENGTH_LONG).show();
    }

    private void setImage(Bitmap image) {
        //ImageView img = findViewById(R.id.imgPhoto);
        //img.setImageBitmap(image);
    }
}
