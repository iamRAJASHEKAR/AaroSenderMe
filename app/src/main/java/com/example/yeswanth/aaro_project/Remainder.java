package com.example.yeswanth.aaro_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ravali on 1/23/2018.
 */

public class Remainder extends HomeScreen {


    Button backbutton, send;
    EditText your_email_address;
    ImageView image1;
    Toolbar toolbar;
    RelativeLayout rl1;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);

        initilize();

     /*   backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Remainder.this, HomeScreen.class);
                startActivity(intent);
            }
        });*/
        your_email_address = (EditText) findViewById(R.id.your_email_address);
        send = (Button) findViewById(R.id.send);
        Texteatcher();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Remainder.this, RemainderSent.class);
                startActivity(i);
               /* final String email = your_email_address.getText().toString();
                if (!isValidEmail(email)) {
                    your_email_address.setError("Invalid Email");
                } else
                    {
                    Intent i = new Intent(Remainder.this, RemainderSent.class);
                    startActivity(i);
                }*/
            }

          /*  private boolean isValidEmail(String email) {
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }*/
        });
    }

    private void initilize() {

        rl1 = (RelativeLayout) findViewById(R.id.imgrelative);
        toolbar = (Toolbar)findViewById(R.id.remindertoolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        image1 = (ImageView) findViewById(R.id.image1);
        image1.setBackgroundResource(R.drawable.ic_back);
        image1.setVisibility(View.VISIBLE);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Remainder.this, Login.class);
                startActivity(intent);
                finish();
                onBackPressed();
            }
        });

        textView1 = (TextView) findViewById(R.id.text1);
        textView1.setText(R.string.remainder);
        textView1.setTextColor(getResources().getColor(R.color.colorBlack));
        textView1.setVisibility(View.VISIBLE);
    }

    public void Texteatcher() {

        your_email_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String email = your_email_address.getText().toString();
                if (!isValidEmail(email)) {
                    your_email_address.setError("Invalid Email id");
                    send.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                String email = your_email_address.getText().toString();
                if (isValidEmail(email)) {
                    send.setEnabled(true);

                }
            }
        });

    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}