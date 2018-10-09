package com.barbaro.cursoandroid.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.barbaro.cursoandroid.R;

public class PhotoActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CODE = 1;

    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imgPhoto = findViewById(R.id.imgPhoto);

        takePhoto();
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_IMAGE_CODE);
        } else {
            showMessage("No se abrio la camara");
        }
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_IMAGE_CODE:
                if(resultCode == RESULT_OK){
                    Bundle params = data.getExtras();
                    if(params != null){
                        Bitmap image = (Bitmap) params.get("data");
                        setImage(image);
                    }
                } else {
                    showMessage("Cancelo la operación");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setImage(Bitmap image) {
        imgPhoto.setImageBitmap(image);
    }
}
