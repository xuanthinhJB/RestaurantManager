package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.activity.TableDetailsActivity;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter.TableAdapter;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog.ActiveNewTable;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog.AddMoreTable;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.TableModel;

import java.util.ArrayList;

/**
 * Created by phanx on 31/12/2017.
 */

public class HomeFragment extends Fragment {
    GridView grvTable;
    ArrayList<TableModel> arrTable;
    TableAdapter tableAdapter;

    DatabaseHelper myDatabase;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fabAddGoods = view.findViewById(R.id.fabAddTable);
        fabAddGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMoreTable.class);
                startActivity(intent);
            }
        });

        // get table from database and put to gridview
        myDatabase = new DatabaseHelper(getActivity());

        grvTable = view.findViewById(R.id.grvTable);
        arrTable = new ArrayList<>();

        getArrayTable();
        if (arrTable.size() == 0) {
            for (int i = 1; i < 11; i ++) {
                TableModel table = new TableModel(i,"Table " + i, 0 , "", 0, 0, false);
                myDatabase.addTable(table);
            }
            arrTable = (ArrayList<TableModel>) myDatabase.getAllTables();
        }
        tableAdapter = new TableAdapter(getActivity(), R.layout.table_adapter_item, arrTable);
        grvTable.setAdapter(tableAdapter);

        grvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arrTable.get(position).isTableActive()) {
                    Intent intent = new Intent(getActivity(), TableDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("table", arrTable.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), ActiveNewTable.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("table", arrTable.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });

        return view;
    }

    public void getArrayTable() {
        arrTable = (ArrayList<TableModel>) myDatabase.getAllTables();
    }

    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences setFragmentTag = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setFragmentTag.edit();
        editor.putString("fragment_tag", "home");
        editor.apply();
    }
}
