package com.example.yeswanth.aaro_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import FONTS.ButtonFontStyle;

public class About_screen extends AppCompatActivity implements SideMenuFragment.FragmentDrawerListener  {

    public static boolean check = true;
    ImageView image1;
    RelativeLayout rl1;
    TextView textView1,terms,privacy,releasenote;
    Toolbar toolbar;
    DrawerLayout drawer;
    private SideMenuFragment sideMenuFragment;
    ButtonFontStyle feedbackbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);

        terms = (TextView)findViewById(R.id.termstext);
        privacy=(TextView)findViewById(R.id.privacypolicy2);
        releasenote=(TextView)findViewById(R.id.releasenote);

        terms.setTypeface(terms.getTypeface(), Typeface.BOLD);
        privacy.setTypeface(privacy.getTypeface(), Typeface.BOLD);
        releasenote.setTypeface(releasenote.getTypeface(), Typeface.BOLD);


        initilize();
        initilizedrawer();

        feedbackbutton = (ButtonFontStyle)findViewById(R.id.feedbackbutton);
        feedbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(About_screen.this,Feedback.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initilize() {
        rl1 = (RelativeLayout)findViewById(R.id.imgrelative);

        image1 = (ImageView)findViewById(R.id.image1);
        image1.setBackgroundResource(R.drawable.ic_menu);
        image1.setVisibility(View.VISIBLE);

        textView1 = (TextView)findViewById(R.id.text1);
        textView1.setText(R.string.about);
        textView1.setVisibility(View.VISIBLE);

    }

    private void initilizedrawer(){

        drawer = (DrawerLayout)findViewById(R.id.drawerlayoutabout);
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
}
