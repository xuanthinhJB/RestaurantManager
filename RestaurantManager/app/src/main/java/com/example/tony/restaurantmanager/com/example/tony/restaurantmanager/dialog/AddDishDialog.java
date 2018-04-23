package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Dishes;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by phanx on 09/01/2018.
 */

public class AddDishDialog extends Activity {
    public static final int ACTION_PICK_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    private ByteArrayOutputStream imgDishOutputSteam = new ByteArrayOutputStream();

    private ImageView imgDish;
    private EditText txtAddDishName;
    private EditText txtAddDishUnit;
    private EditText txtAddDishPrice;
    private Button btnConfirmAddDish;
    private Button btnCancelAddDish;
    private Button btnGallery;
    private Button btnCamera;

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_add_dish_layout);

        addUI();
        addEvent();
    }

    public void addUI() {
        imgDish = findViewById(R.id.imgAddDish);
        btnGallery = findViewById(R.id.btnBrownForPhoto2);
        btnCamera = findViewById(R.id.btnTakePhoto2);
        txtAddDishName = findViewById(R.id.txtAddDishName);
        txtAddDishUnit = findViewById(R.id.txtAddDishPrice);
        btnConfirmAddDish = findViewById(R.id.btnConfirmAddDish);
        btnCancelAddDish = findViewById(R.id.btnCancelAddDish);
    }

    public void addEvent() {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), ACTION_PICK_IMAGE);
            }
        });

        btnConfirmAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtAddDishName.getText().toString().equals("") &&
                        !txtAddDishPrice.getText().toString().equals("") &&
                        !txtAddDishUnit.getText().toString().equals("")) {
                    Dishes dish = new Dishes();
                    dish.setDishImage(imgDishOutputSteam.toByteArray());
                    dish.setDishName(txtAddDishName.getText().toString());
                    dish.setDishUnit(txtAddDishUnit.getText().toString());
                    dish.setDishPrice(Long.parseLong(txtAddDishPrice.getText().toString()));

                    databaseHelper.addDishes(dish);
                    finish();
                } else {
                    openDialog();
                }

            }
        });

        btnCancelAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void openDialog(){
        Toast.makeText(this, R.string.text_incomplete, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_PICK_IMAGE) {
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                Bitmap photo = BitmapFactory.decodeStream(inputStream);
                photo.compress(Bitmap.CompressFormat.PNG, 100, imgDishOutputSteam);
                imgDish.setImageBitmap(photo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            photo.compress(Bitmap.CompressFormat.PNG, 100, imgDishOutputSteam);
            imgDish.setImageBitmap(photo);
        }
    }
}
