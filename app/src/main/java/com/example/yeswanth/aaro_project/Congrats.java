package com.example.yeswanth.aaro_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import FONTS.TextViewFontStyle;

/**
 * Created by ravali on 1/25/2018.
 */

public class Congrats extends AppCompatActivity {


    TextView congratstext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congrats);

        congratstext = (TextView) findViewById(R.id.congrats_text1);
        congratstext.setTypeface(congratstext.getTypeface(), Typeface.BOLD);

        Thread t = new Thread() {

            public void run() {

                try {
                    sleep(4000);
                    finish();
                    Intent cv = new Intent(Congrats.this, HomeScreen.class/*otherclass*/);
                    startActivity(cv);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

}

