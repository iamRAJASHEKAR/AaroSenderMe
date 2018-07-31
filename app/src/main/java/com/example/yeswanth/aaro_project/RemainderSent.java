package com.example.yeswanth.aaro_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by ravali on 1/23/2018.
 */

public class RemainderSent extends Remainder {
    Button con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaindersent);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);
        con = (Button) findViewById(R.id.con);
        con.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemainderSent.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
