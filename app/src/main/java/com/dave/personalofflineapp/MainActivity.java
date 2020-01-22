package com.dave.personalofflineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dave.personalofflineapp.DatabaseHelper.DatabaseHelperClass;
import com.dave.personalofflineapp.Pojo.PersonalDetails;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText etPhoneNumber,etLName,etFName;

    private DatabaseHelperClass databaseHelperClass;

    private byte[] imageInByte;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 2;
    private static final int SELECT_PHOTO = 1;
    private static final int CAPTURE_PHOTO = 2;
    private Bitmap thumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelperClass = new DatabaseHelperClass(this);

        imageView = findViewById(R.id.imageView);
        etFName = findViewById(R.id.etFName);
        etLName = findViewById(R.id.etLName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckPermission();

            }
        });


    }

    private void CheckPermission() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

            //Permission is already granted
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, SELECT_PHOTO);


        }else {
            //Request Camera Permissions
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);

        }


    }

    public void Save(View view) {

        if (imageView.getDrawable() != null){

            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            imageInByte = baos.toByteArray();



        }

        String txtFName = etFName.getText().toString();
        String txtLName = etLName.getText().toString();
        String txtPhone = etPhoneNumber.getText().toString();

        if (!TextUtils.isEmpty(txtFName) && !TextUtils.isEmpty(txtLName) && !TextUtils.isEmpty(txtPhone) && imageInByte != null){

            databaseHelperClass.AddPersonalinfo(txtFName, txtLName, txtPhone,imageInByte);

            etFName.setText("");
            etPhoneNumber.setText("");
            etLName.setText("");

            Intent intent = new Intent(getApplicationContext(), AllPersons.class);
            startActivity(intent);

        }else {
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_PHOTO){
            if(resultCode == RESULT_OK) {
                try {

                    final Uri imageUri = data.getData();

                    //set profile picture form gallery
                    thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imageView.setImageBitmap(thumbnail);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
