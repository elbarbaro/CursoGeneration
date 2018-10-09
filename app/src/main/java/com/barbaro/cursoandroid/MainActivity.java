package com.barbaro.cursoandroid;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.barbaro.cursoandroid.home.HomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String file_name = "curso_android";
    public static final String SESSION_KEY = "open_session";

    private static final int REQUEST_LOCATION = 9001;

    private EditText editUser;
    private EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(file_name, 0);
        if(preferences.contains(SESSION_KEY)){
            navigateHome();
        }

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permission != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
        }
        editUser  = findViewById(R.id.editUser);
        editPass = findViewById(R.id.editPass);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    private void navigateHome() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_LOCATION:
                if(grantResults[0] == 0){
                    getLocation();
                } else {
                    showMessage("Son importantes los permisos de ubicacion");
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getLocation() {
        // DEFINIR LOGICA

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                login();
                break;
        }
    }

    private void login() {
        // HACER LOGIN
        String user = editUser.getText().toString();
        String pass = editPass.getText().toString();

        if(user.equals("barbaro") && pass.equals("123456")){
            //NAVEGAR A SEGUNDA PANTALLA
            showMessage(getString(R.string.tag_success_login));
            savePreference();
            navigateHome();
        } else {
            showMessage(getString(R.string.tag_fail_login));
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void savePreference(){
        SharedPreferences preferences = getSharedPreferences(file_name, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SESSION_KEY, true);
        editor.apply();
    }
}
