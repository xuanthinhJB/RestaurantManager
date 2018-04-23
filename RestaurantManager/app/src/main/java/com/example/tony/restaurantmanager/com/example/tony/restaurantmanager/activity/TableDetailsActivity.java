package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter.DishOnTableAdapter;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog.ManageCustomersDialog;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog.ShowListDishDialog;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.interfaces.ActionOnShowTableDetail;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Dishes;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.DishesNameOnChosing;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.DishesOnTable;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.TableModel;

import java.util.ArrayList;
import java.util.List;

public class TableDetailsActivity extends AppCompatActivity implements ActionOnShowTableDetail {
    private TableModel currentTable;
    private ImageView imgTable;
    private TextView txtTableDetailName;
    private TextView txtPeopleSum;
    private TextView txtBillSum;
    private Button btnAddMoreDish, btnManageCustomers, btnShowBill, btnTransportTable, btnCloseTable, btnDeleteTable;
    private LinearLayout layoutDishItemInTableDetail;

    private ListView lvDishOnTable;
    private ArrayList<DishesOnTable> arrDishesOnTable;
    private DishOnTableAdapter dishOnTableAdapter;

    public static final int ADD_DISH_CODE = 1;

    private DatabaseHelper myDatabase = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_details);

        addUIControls();
        addEvents();
    }

    @SuppressLint("SetTextI18n")
    public void addUIControls() {
        imgTable = findViewById(R.id.imgTableDetails);
        txtTableDetailName = findViewById(R.id.txtTableDetailName);
        txtPeopleSum = findViewById(R.id.txtPeopleOnTable);
        txtBillSum = findViewById(R.id.txtBillSum);
        layoutDishItemInTableDetail = findViewById(R.id.layoutDishItemInTableDetail);
        lvDishOnTable = findViewById(R.id.lvDishOnTable);
        //lvDishOnTable.addHeaderView();

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        currentTable = (TableModel) bundle.getSerializable("table");
        txtTableDetailName.setText(currentTable.getTableName());
        txtPeopleSum.setText(" - " + String.valueOf(currentTable.getTableNumberOfPeople()));
        txtBillSum.setText(getBillSum(currentTable.getTableDishes()) + "");

        arrDishesOnTable = new ArrayList<>();
        arrDishesOnTable = getDishArray(currentTable.getTableDishes());
        dishOnTableAdapter = new DishOnTableAdapter(TableDetailsActivity.this, R.layout.dish_adapter_item_in_table_detail, arrDishesOnTable);
        lvDishOnTable.setAdapter(dishOnTableAdapter);
    }

    public void addEvents(){
        lvDishOnTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // show item deal dialog
                Animation slideLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_to_left);
                Animation slideRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_to_right);

                if (layoutDishItemInTableDetail.getVisibility() == View.GONE) {
                    layoutDishItemInTableDetail.startAnimation(slideLeft);
                    layoutDishItemInTableDetail.setVisibility(View.VISIBLE);
                } else {
                    layoutDishItemInTableDetail.startAnimation(slideRight);
                    layoutDishItemInTableDetail.setVisibility(View.GONE);
                }
            }
        });

        btnAddMoreDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the table of dish to add
                Intent intent = new Intent(TableDetailsActivity.this, ShowListDishDialog.class);
                startActivityForResult(intent, ADD_DISH_CODE);
            }
        });

        btnManageCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageCustomersDialog manageCustomersDialog = new ManageCustomersDialog(TableDetailsActivity.this, txtPeopleSum, currentTable);
                manageCustomersDialog.showManageCustomerDialog();
            }
        });

        btnShowBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnTransportTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCloseTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDeleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public long getBillSum(String dishes) {
        List<Dishes> allDish = myDatabase.getAllDishes();
        long billSum = 0;
        String[] listDish = dishes.split(";");
        for (String aListDish : listDish) {
            for (int j = 0; j < allDish.size(); j++) {
                if (aListDish.equals(allDish.get(j).getDishName())) {
                    billSum = billSum + allDish.get(j).getDishPrice();
                }
            }
        }
        return billSum;
    }

    public ArrayList<DishesOnTable> getDishArray(String dishesInString) {
        ArrayList<DishesOnTable> arrDishes = new ArrayList<>();
        List<Dishes> allDish = myDatabase.getAllDishes();

        // slice long-string into individual string
        String[] listDish = dishesInString.split(";");
        for (String aListDish : listDish) {
            DishesOnTable dishesOnTable = new DishesOnTable();
            dishesOnTable.setDishName(aListDish);
            dishesOnTable.setDishAmount(1);
            arrDishes.add(dishesOnTable);
        }

        // count every kind of string
        for (int j = 0; j < arrDishes.size(); j++) {
            for (int k = 0; k < arrDishes.size(); k++) {
                if ((j < k) && arrDishes.get(j).getDishName().equals(arrDishes.get(k).getDishName())) {
                    arrDishes.get(j).setDishAmount(arrDishes.get(j).getDishAmount() + 1);
                    arrDishes.remove(k);
                }
            }
        }

        // set the money
        for (int q = 0; q < arrDishes.size(); q++) {
            for (int r = 0; r < allDish.size(); r++) {
                if (arrDishes.get(q).getDishName().equals(allDish.get(r).getDishName())) {
                    arrDishes.get(q).setDishBillMember(allDish.get(r).getDishPrice() * arrDishes.get(q).getDishAmount());
                }
            }
        }

        return arrDishes;
    }

    @Override
    public void increaseOneDish(int positon, int amountOfDish) {
        arrDishesOnTable.get(positon).setDishAmount(amountOfDish + 1);
        dishOnTableAdapter.notifyDataSetChanged();
    }

    @Override
    public void decreaseOneDish(int position, int amountOfDish) {
        arrDishesOnTable.get(position).setDishAmount(amountOfDish - 1);
    }

    @Override
    public void deleteDish(int position, String dishName) {
        arrDishesOnTable.remove(position);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TableDetailsActivity.this, ManagerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get result add dish
        if (requestCode == ADD_DISH_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<DishesNameOnChosing> arrDishesChosen = data.getParcelableExtra("add_dish_result");
                for (int i = 0; i < arrDishesChosen.size(); i++) {
                    for (int j = 0; j < arrDishesChosen.get(i).getDishAmount(); j++) {
                        currentTable.setTableDishes(currentTable.getTableDishes() + arrDishesChosen.get(i).getDishName() + ";" );
                    }
                }

                Toast.makeText(TableDetailsActivity.this, arrDishesChosen.size() + " dishes have been added", Toast.LENGTH_LONG).show();
            }
        }
    }

    // helper class

}
