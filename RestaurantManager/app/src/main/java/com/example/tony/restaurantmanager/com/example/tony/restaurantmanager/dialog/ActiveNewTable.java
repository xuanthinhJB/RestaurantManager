package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.activity.TableDetailsActivity;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.TableModel;

/**
 * Created by phanx on 16/01/2018.
 */

public class ActiveNewTable extends Activity {

    private TextView txtMessageActiveTable;
    private EditText txtInputNumbersOfCustomers;
    private Button btnConfirmActiveTable;
    private Button btnCancelActiveTable;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_active_new_table);

        addUI();
        addEvent();
    }

    public void addUI() {
        txtMessageActiveTable = findViewById(R.id.txtMessageActiveTable);
        txtInputNumbersOfCustomers = findViewById(R.id.txtInputNumbersCustomers);
        btnConfirmActiveTable = findViewById(R.id.btnConfirmActiveTable);
        btnCancelActiveTable = findViewById(R.id.btnCancelActiveTable);
    }

    @SuppressLint("SetTextI18n")
    public void addEvent() {
        Intent intentReceive = getIntent();
        Bundle bundle = intentReceive.getExtras();

        final TableModel table = (TableModel) bundle.getSerializable("table");
        txtMessageActiveTable.setText(txtMessageActiveTable.getText().toString() + " " + table.getTableID());

        btnConfirmActiveTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTableInfo(table);
                Intent intentSend = new Intent(ActiveNewTable.this, TableDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("table", table);
                intentSend.putExtras(bundle);
                startActivity(intentSend);
            }
        });

        btnCancelActiveTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setTableInfo(TableModel table) {
        table.setTableActive(true);
        if (!txtInputNumbersOfCustomers.getText().toString().equals("")) {
            int nO = Integer.parseInt(String.valueOf(txtInputNumbersOfCustomers.getText()));
            table.setTableNumberOfPeople(nO);
        }

        DatabaseHelper myDB = new DatabaseHelper(this);
        myDB.updateTable(table);
    }
}
