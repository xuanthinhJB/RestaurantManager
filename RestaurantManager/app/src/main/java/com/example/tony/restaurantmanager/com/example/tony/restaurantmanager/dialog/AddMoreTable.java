package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.TableModel;

/**
 * Created by phanx on 10/01/2018.
 */

public class AddMoreTable extends Activity{
    private EditText txtNumberOfTableToAdd;
    private Button btnOK;
    private Button btnCancel;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_input_number_tables_layout);

        addUI();
        addEvent();
    }

    public void addUI() {
        btnOK = findViewById(R.id.btnConfirmAddTable);
        btnCancel = findViewById(R.id.btnCancelAddTable);
        txtNumberOfTableToAdd = findViewById(R.id.txtInputNumberOfTable);
    }

    public void addEvent() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!txtNumberOfTableToAdd.getText().toString().equals("") && Integer.parseInt(txtNumberOfTableToAdd.getText().toString()) < 0) {
                    showWrongNumberWarning();
                } else if (!txtNumberOfTableToAdd.getText().toString().equals("") && Integer.parseInt(txtNumberOfTableToAdd.getText().toString()) >
                        0) {
                    for (int i = 0; i < Integer.parseInt(txtNumberOfTableToAdd.getText().toString()); i++) {
                        TableModel table = new TableModel(i,"Table " + i, 0 , "", 0, 0, false);
                        databaseHelper.addTable(table);
                    }
                }
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showWrongNumberWarning() {
        Toast.makeText(this, R.string.input_minus_int, Toast.LENGTH_LONG).show();
    }
}
