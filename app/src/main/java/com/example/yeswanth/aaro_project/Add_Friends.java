package com.example.yeswanth.aaro_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Add_Friends extends AppCompatActivity {

    ImageView image1;
    RelativeLayout rl1;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__friends);
        initilize();
    }
    private void initilize() {

        rl1 = (RelativeLayout)findViewById(R.id.imgrelative);

        image1 = (ImageView)findViewById(R.id.image1);
        image1.setBackgroundResource(R.drawable.ic_back);
        image1.setVisibility(View.VISIBLE);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Friends.this,Friends.class);
                startActivity(intent);
                finish();
            }
        });

        textView1 = (TextView)findViewById(R.id.text1);
        textView1.setText(R.string.addfriend);
        textView1.setVisibility(View.VISIBLE);
    }
}
