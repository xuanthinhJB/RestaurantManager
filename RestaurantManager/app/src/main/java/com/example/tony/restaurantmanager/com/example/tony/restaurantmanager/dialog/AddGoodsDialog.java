package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Goods;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by phanx on 06/01/2018.
 */

public class AddGoodsDialog extends Activity {
    public static final int ACTION_PICK_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    private ByteArrayOutputStream imgGoodsOutputSteam = new ByteArrayOutputStream();

    private EditText txtGoodsName;
    private ImageView imgGoods;
    private Button btnGallery;
    private Button btnCamera;
    private EditText txtGoodsQuantity;
    private EditText txtGoodsUnit;
    private EditText txtGoodsPrice;
    private EditText txtGoodsEXPDate;
    private Spinner spnDate, spnMonth, spnYear;
    private Button btnConfirmAdd;
    private Button btnCancelAdd;

    private ArrayList<String> listDate, listMonth, listYear;
    private ArrayAdapter<String> adapterDate, adapterMonth, adapterYear;

    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    private int date = 0, month = 0, year = 0;
    private String currentDate, currentMonth, currentYear;

    private Dialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_add_goods_layout);

        addUI();
        addEvent();
    }

    public void addUI() {
        txtGoodsName = findViewById(R.id.txtAddGoodsName);
        imgGoods = findViewById(R.id.imgAddGoods);
        imgGoods.setImageResource(R.drawable.ic_menu_camera);
        btnGallery = findViewById(R.id.btnBrownForPhoto);
        btnCamera = findViewById(R.id.btnTakePhoto);
        txtGoodsQuantity = findViewById(R.id.txtAddGoodsQuantity);
        txtGoodsUnit = findViewById(R.id.txtAddGoodsUnit);
        txtGoodsPrice = findViewById(R.id.txtAddGoodsPrice);
        spnDate = findViewById(R.id.spnDate);
        spnMonth = findViewById(R.id.spnMonth);
        spnYear = findViewById(R.id.spnYear);
        btnConfirmAdd = findViewById(R.id.btnConfirmAddGoods);
        btnCancelAdd = findViewById(R.id.btnCancelAddGoods);

        listDate = new ArrayList<>();
        listMonth = new ArrayList<>();
        listYear = new ArrayList<>();

        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                listMonth.add("0" + i);
            } else listMonth.add("" + i);
        }

        for (int i = 1; i < 32; i++) {
            if (i < 10) {
                listDate.add("0" + i);
            } else listDate.add("" + i);
        }

        for (int i = 2017; i < 2031; i++) {
            listYear.add("" + i);
        }

        adapterDate = new ArrayAdapter<String>(AddGoodsDialog.this, R.layout.spinner_item, listDate);
        adapterMonth = new ArrayAdapter<String>(AddGoodsDialog.this, R.layout.spinner_item, listMonth);
        adapterYear = new ArrayAdapter<String>(AddGoodsDialog.this, R.layout.spinner_item, listYear);

        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnDate.setAdapter(adapterDate);
        spnMonth.setAdapter(adapterMonth);
        spnYear.setAdapter(adapterYear);

        // set the default selection for all spinners
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = dateFormat.format(calendar.getTime());

        currentYear = currentTime.substring(0, 4);
        currentMonth = currentTime.substring(5,7);
        currentDate = currentTime.substring(8,10);

        ArrayAdapter adapter1 = (ArrayAdapter) spnDate.getAdapter();
        ArrayAdapter adapter2 = (ArrayAdapter) spnMonth.getAdapter();
        ArrayAdapter adapter3 = (ArrayAdapter) spnYear.getAdapter();

        int positionDate = adapter1.getPosition(currentDate);
        int positionMonth = adapter2.getPosition(currentMonth);
        int positionYear = adapter3.getPosition(currentYear);

        spnDate.setSelection(positionDate);
        spnMonth.setSelection(positionMonth);
        spnYear.setSelection(positionYear);


    }

    public void addEvent() {
        // event click on button gallery
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), ACTION_PICK_IMAGE);
            }
        });

        // event click on button camera
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


        // event click on spinner
        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = Integer.parseInt((String) spnMonth.getItemAtPosition(position));

                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    listDate.clear();
                    for (int i = 1; i < 32; i++) {
                        if (i < 10) {
                            listDate.add("0" + i);
                        } else listDate.add("" + i);
                        adapterDate.notifyDataSetChanged();
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    listDate.clear();
                    for (int i = 1; i < 31; i++) {
                        if (i < 10) {
                            listDate.add("0" + i);
                        } else listDate.add("" + i);
                        adapterDate.notifyDataSetChanged();
                    }
                } else if (month == 2 && year % 4 == 0) {
                    listDate.clear();
                    for (int i = 1; i < 30; i++) {
                        if (i < 10) {
                            listDate.add("0" + i);
                        } else listDate.add("" + i);
                        adapterDate.notifyDataSetChanged();
                    }
                } else if (month == 2 && year % 4 != 0) {
                    listDate.clear();
                    for (int i = 1; i < 29; i++) {
                        if (i < 10) {
                            listDate.add("0" + i);
                        } else listDate.add("" + i);
                        adapterDate.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = Integer.parseInt((String) spnYear.getItemAtPosition(position));

                if (month == 2 && year % 4 == 0) {
                    listDate.clear();
                    for (int i = 1; i < 30; i++) {
                        if (i < 10) {
                            listDate.add("0" + i);
                        } else listDate.add("" + i);
                        adapterDate.notifyDataSetChanged();
                    }
                } else if (month == 2 && year % 4 != 0) {
                    listDate.clear();
                    for (int i = 1; i < 29; i++) {
                        if (i < 10) {
                            listDate.add("0" + i);
                        } else listDate.add("" + i);
                        adapterDate.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                date = Integer.parseInt((String) spnDate.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtGoodsName.getText().toString().equals("") &&
                        !txtGoodsQuantity.getText().toString().equals("") &&
                        !txtGoodsUnit.getText().toString().equals("") &&
                        !txtGoodsPrice.getText().toString().equals("")) {
                    Goods goods = new Goods();
                    goods.setGoodsImage(imgGoodsOutputSteam.toByteArray());
                    goods.setGoodsName(txtGoodsName.getText().toString());
                    goods.setGoodsQuantity(Float.valueOf(txtGoodsQuantity.getText().toString()));
                    goods.setGoodsUnit(txtGoodsUnit.getText().toString());
                    goods.setGoodsPrice(Long.parseLong(txtGoodsPrice.getText().toString()));

                    String string_dateRCP = currentDate + "-" + currentMonth + "-" + currentYear;
                    goods.setGoodsReceiptedDate(string_dateRCP);

                    String string_dateEXP = date + "-" + month + "-" + year;
                    goods.setGoodsExpiredDate(string_dateEXP);

                    databaseHelper.addGoods(goods);
                } else {
                    openDialog();
                }

            }
        });

        btnCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGoodsDialog.super.onBackPressed();
            }
        });
    }

    public void openDialog() {
//        dialog = new Dialog(this);
//        dialog.setTitle(R.string.text_incomplete);
//        dialog.show();
        Toast.makeText(this, R.string.text_incomplete, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_PICK_IMAGE) {
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                Bitmap photo = BitmapFactory.decodeStream(inputStream);
                photo.compress(Bitmap.CompressFormat.PNG, 100, imgGoodsOutputSteam);
                imgGoods.setImageBitmap(photo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            photo.compress(Bitmap.CompressFormat.PNG, 100, imgGoodsOutputSteam);
            imgGoods.setImageBitmap(photo);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
