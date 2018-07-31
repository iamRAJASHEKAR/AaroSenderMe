package com.example.yeswanth.aaro_project;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Database.DatabaseManager;

public class HomeScreen extends AppCompatActivity implements SideMenuFragment.FragmentDrawerListener {

    public static boolean check = true;
    ImageView image1, image2, image3;
    Toolbar toolbar;
    DrawerLayout drawer;
    private SideMenuFragment sideMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        check = false;
        initilize();
        initilizedrawer();

        //DatabaseManager.getInstance().getalluser();

    }

    private void initilize() {
        image1 = (ImageView) findViewById(R.id.image1);
        image1.setBackgroundResource(R.drawable.ic_menu);
        image1.setVisibility(View.VISIBLE);

        image2 = (ImageView) findViewById(R.id.image2);
        image2.setBackgroundResource(R.drawable.aaro_logo);
        image2.setVisibility(View.VISIBLE);

        image3 = (ImageView) findViewById(R.id.image3);
        image3.setBackgroundResource(R.drawable.ic_bell);
        image3.setVisibility(View.VISIBLE);


    }

    private void initilizedrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        sideMenuFragment = (SideMenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        sideMenuFragment.setup(R.id.fragment1, drawer, toolbar);
        sideMenuFragment.setFragmentDrawerListner(this);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        check = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        check = true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Toast.makeText(this, "doo", Toast.LENGTH_SHORT).show();
        finish();
    }
}
