package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.TableModel;

/**
 * Created by phanx on 04/02/2018.
 */

public class ManageCustomersDialog {
    private Context context;
    private TextView txtCustomer;
    private TableModel tableInManage;
    public ManageCustomersDialog(Context context, TextView txtCustomer, TableModel tableInManage) {
        this.context = context;
        this.txtCustomer = txtCustomer;
        this.tableInManage = tableInManage;
    }

    @SuppressLint("InflateParams")
    public void showManageCustomerDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater dialogInflater = ((Activity) context).getLayoutInflater();
        View alertView = dialogInflater.inflate(R.layout.dialog_manage_customers, null);
        alertDialog.setView(alertView);

        final AlertDialog showDialog = alertDialog.show();

        Button btnConfirm = alertView.findViewById(R.id.btnConfirmManageCustomers);
        Button btnCancel = alertView.findViewById(R.id.btnCancelManageCustomers);
        final EditText txtInputNumberOfCustomers = alertView.findViewById(R.id.txtManageCustomers);
        Button btnIncreaseCustomers = alertView.findViewById(R.id.btnIncreaseCustomers);
        Button btnDecreaseCustomers = alertView.findViewById(R.id.btnDecreaseCustomers);

        final DatabaseHelper myDatabase = new DatabaseHelper(context);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCustomer.setText(txtInputNumberOfCustomers.getText().toString());
                tableInManage.setTableNumberOfPeople(Integer.parseInt(txtInputNumberOfCustomers.getText().toString()));
                myDatabase.updateTable(tableInManage);
                showDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog.dismiss();
            }
        });

        btnIncreaseCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInputNumberOfCustomers.setText(Integer.parseInt(txtInputNumberOfCustomers.getText().toString()) + 1);
            }
        });

        btnDecreaseCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(txtInputNumberOfCustomers.getText().toString()) > 1) {
                    txtInputNumberOfCustomers.setText(Integer.parseInt(txtInputNumberOfCustomers.getText().toString()) - 1);
                } else if (Integer.parseInt(txtInputNumberOfCustomers.getText().toString()) == 1) {
                    Toast.makeText(context, R.string.toast_customers_less_than_1, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
