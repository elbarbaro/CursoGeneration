package com.barbaro.cursoandroid.home;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.barbaro.cursoandroid.MainActivity;
import com.barbaro.cursoandroid.MessageRepository;
import com.barbaro.cursoandroid.R;
import com.barbaro.cursoandroid.db.OnSQLDatabaseListener;
import com.barbaro.cursoandroid.home.adapters.MessageAdapter;
import com.barbaro.cursoandroid.models.Message;
import com.barbaro.cursoandroid.profile.PhotoActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnSQLDatabaseListener {

    private ListView listMessages;
    private MessageRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        repository = new MessageRepository(this, this);

        listMessages = findViewById(R.id.listMessages);
        listMessages.setAdapter(fillMessages());
    }

    private ListAdapter fillMessages() {
        return new MessageAdapter(this, repository.getLocalMessages());
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
                logout();
                //showMessage("Actualizar");
                return true;
            case R.id.delete:
                createMessages();
                //showMessage("Eliminar");
                return true;
            default:
                return false;
        }
    }

    private void createMessages() {
        repository.generateMessages();
        listMessages.setAdapter(fillMessages());
    }

    private void logout() {
        SharedPreferences preferences = getSharedPreferences(MainActivity.file_name, 0);
        if(preferences.contains(MainActivity.SESSION_KEY)){
            preferences.edit()
                    .remove(MainActivity.SESSION_KEY)
                    .apply();
            navigateToLogin();
        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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

    @Override
    public void onMessage(String message) {
        showMessage(message);
    }
}
