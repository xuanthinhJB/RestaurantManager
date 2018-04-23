package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.tony.restaurantmanager.R;

public class LaunchScene extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_scene);

        //addControl();

        // delay transaction activity after 2 seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchScene.this, ManagerActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }

    private void addControl() {
        TextView txtCompanyName = findViewById(R.id.txtCompanyName);

        Animation anim = AnimationUtils.loadAnimation(LaunchScene.this, R.anim.fade);
        //txtCompanyName.setAnimation(anim);
    }
}
