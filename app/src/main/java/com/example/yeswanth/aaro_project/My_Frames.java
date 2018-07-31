package com.example.yeswanth.aaro_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class My_Frames extends AppCompatActivity implements SideMenuFragment.FragmentDrawerListener{


    public static boolean check = true;
    ImageView image1;
    RelativeLayout rl1;
    TextView textView1,pairtext;
    Toolbar toolbar;
    DrawerLayout drawer;
    private SideMenuFragment sideMenuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__frames);

        check = false;
       initilize();
        initilizedrawer();

        pairtext = (TextView)findViewById(R.id.pairtext);
        pairtext.setTypeface(pairtext.getTypeface(), Typeface.BOLD);
        pairtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_Frames.this,My_Frames_Pairing.class);
                startActivity(intent);
            }
        });
    }

    private void initilize() {

        rl1 = (RelativeLayout)findViewById(R.id.imgrelative);

        image1 = (ImageView)findViewById(R.id.image1);
        image1.setBackgroundResource(R.drawable.ic_menu);
        image1.setVisibility(View.VISIBLE);

        textView1 = (TextView)findViewById(R.id.text1);
        textView1.setText(R.string.myframes);
        textView1.setVisibility(View.VISIBLE);
    }
    private void initilizedrawer(){
        drawer = (DrawerLayout)findViewById(R.id.myframesdrawer);
        sideMenuFragment = (SideMenuFragment)getSupportFragmentManager().findFragmentById(R.id.fragment1);
        sideMenuFragment.setup(R.id.fragment1,drawer,toolbar);
        sideMenuFragment.setFragmentDrawerListner(this);
        rl1.setOnClickListener(new View.OnClickListener() {
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
}
