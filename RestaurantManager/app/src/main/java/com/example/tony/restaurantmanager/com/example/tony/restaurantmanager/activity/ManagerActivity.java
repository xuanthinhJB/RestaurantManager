package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter.TableAdapter;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment.DishesFragment;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment.GoodsFragment;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment.HomeFragment;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment.InfoFragment;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment.ReportFragment;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment.SettingFragment;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment.ShowMoreAppFragment;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.TableModel;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int NUMBER_OF_TABLES;

    public static String DATABASE_NAME = "Data.sqlite";
    String DATABASE_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    DatabaseHelper myDatabase;
    private TableAdapter tableAdapter;
    private ArrayList<TableModel> arrTable;

    private HomeFragment homeFragment;
    private GoodsFragment goodsFragment;
    private DishesFragment dishesFragment;
    private ReportFragment reportFragment;
    private SettingFragment settingFragment;
    private ShowMoreAppFragment showMoreAppFragment;
    private InfoFragment infoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // show UI
        setContentView(R.layout.activity_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, homeFragment).commitAllowingStateLoss();
            this.setTitle(R.string.home);
        } else if (id == R.id.nav_goods) {
            goodsFragment = new GoodsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, goodsFragment).commitAllowingStateLoss();
            this.setTitle(R.string.goods);
        } else if (id == R.id.nav_dishes) {
            dishesFragment = new DishesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, dishesFragment).commitAllowingStateLoss();
            this.setTitle(R.string.dish);
        } else if (id == R.id.nav_report) {
            reportFragment = new ReportFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, reportFragment).commitAllowingStateLoss();
            this.setTitle(R.string.report);
        } else if (id == R.id.nav_setting) {
            settingFragment = new SettingFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, settingFragment).commitAllowingStateLoss();
            this.setTitle(R.string.setting);
        } else if (id == R.id.nav_more_apps) {
            showMoreAppFragment = new ShowMoreAppFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, showMoreAppFragment).commitAllowingStateLoss();
            this.setTitle(R.string.more_apps);
        } else if (id == R.id.nav_info) {
            infoFragment = new InfoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, infoFragment).commitAllowingStateLoss();
            this.setTitle(R.string.about_us);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, homeFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences getFragmentTag = getPreferences(MODE_PRIVATE);
        String tag = getFragmentTag.getString("fragment_tag", "");

        switch (tag) {
            case "home":
                homeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, homeFragment).commitAllowingStateLoss();
                this.setTitle(R.string.home);
                break;
            case "goods":
                goodsFragment = new GoodsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, goodsFragment).commitAllowingStateLoss();
                this.setTitle(R.string.goods);
                break;
            case "dish":
                dishesFragment = new DishesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, dishesFragment).commitAllowingStateLoss();
                this.setTitle(R.string.dish);
                break;
            default: break;
        }
    }
}
