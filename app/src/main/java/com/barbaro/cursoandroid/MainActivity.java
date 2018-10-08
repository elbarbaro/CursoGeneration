package com.barbaro.cursoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.barbaro.cursoandroid.home.HomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editUser;
    private EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser  = findViewById(R.id.editUser);
        editPass = findViewById(R.id.editPass);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
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
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
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
}
