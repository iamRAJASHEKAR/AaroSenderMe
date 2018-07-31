package com.example.yeswanth.aaro_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registeruser extends AppCompatActivity {

    EditText editText1, editText2, editText3;
    ToggleButton toggleButton;


    Spinner spinner;
    Button continuebutton;
    ImageView imageback;
    Toolbar toolbar;
    RelativeLayout rl1;
    TextView textView1, privacy, terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);
        toggleButton = (ToggleButton) findViewById(R.id.toggle_button);

        privacy = (TextView) findViewById(R.id.privacypolicy);
        terms = (TextView) findViewById(R.id.termsofservices);
        privacy.setTypeface(privacy.getTypeface(), Typeface.BOLD);
        terms.setTypeface(terms.getTypeface(), Typeface.BOLD);

        editText1 = (EditText) findViewById(R.id.register_fname);
        editText2 = (EditText) findViewById(R.id.register_lname);
        editText3 = (EditText) findViewById(R.id.register_email);

        Checkfiledsforemptyvalues();

        editText1.addTextChangedListener(mtextWatcher);
        editText2.addTextChangedListener(mtextWatcher);
        editText3.addTextChangedListener(mtextWatcher);

        initilize();
        //  paser();
        // texter();
        // textla();
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setPrompt("Where are you from");
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country)) {
                // countries.add("Where are you from");
                countries.add(country);
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

/*
        List<String> languages = new ArrayList<String>();
        languages.add("Where are you from");
        languages.add("Hindupur");
        languages.add("Yelloti");
        languages.add("Banglore");
        languages.add("Kanalanagar");*/
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();

                if (i > 0) {

                    Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        continuebutton = (Button) findViewById(R.id.button_continue);
        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton.isChecked()) {
                 /*Intent intent =   new Intent(Registeruser.this, signin_cancel.class);

                 intent.putExtra("firstname",editText1);


                 startActivity(intent);

*/
                    String user = editText1.getText().toString().trim();
                    String last = editText2.getText().toString().trim();
                    String mail = editText3.getText().toString().trim();
                    String country = spinner.getSelectedItem().toString();
                    Log.e("country", user + country);

                    Intent intent = new Intent(getApplicationContext(), signin_cancel.class);
                    intent.putExtra("first_name", user);
                    intent.putExtra("last_name", last);
                    intent.putExtra("mail", mail);
                    intent.putExtra("country", country);
                    startActivity(intent);
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    finish();


                } else {
                    //  Toast.makeText(Registeruser.this, "PLease accept our terms of services", Toast.LENGTH_LONG).show();

                    LayoutInflater inflater = LayoutInflater.from(Registeruser.this);
                    final View alertview = inflater.inflate(R.layout.alert_dialog, null);
                    final AlertDialog alertDialog = new AlertDialog.Builder(Registeruser.this).create();
                    alertDialog.setView(alertview);
                    alertDialog.setCancelable(false);
                    alertview.findViewById(R.id.alertbutton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                 /*   AlertDialog.Builder builder = new AlertDialog.Builder(Registeruser.this);
                 //   builder.setTitle(Html.fromHtml("<font color='#993300'>Alert !</font>"));
                    builder.setMessage("Please accept our privacy policy and terms of services");
                    builder.setCancelable(false);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }

                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();*/
                }
            }
        });
        // attaching data adapter to spinner

/*

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton.isActivated()) {
                    continuebutton.setEnabled(true);
                } else {
                    //  Toast.makeText(Registeruser.this, "Agree for terms of services to use our app", Toast.LENGTH_LONG).show();
                }
            }
        });

*/

    }

    public void textla() {
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String fname = editText2.getText().toString().trim();
                if (fname.length() < 2) {
                    editText2.setError("Username too short ");
                    continuebutton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String fname = editText2.getText().toString().trim();
                if (fname.length() > 2) {
                    continuebutton.setEnabled(true);

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

  /*  public void paser() {

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String fname = editText3.getText().toString().trim();
                if (!isValidEmail(fname)) {

                    continuebutton.setEnabled(false);
                    editText3.setError("Invalid Email");

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
                String fname = editText3.getText().toString().trim();
                if (isValidEmail(fname)) {

                    continuebutton.setEnabled(true);
                }

            }
        });

    }


    public void texter() {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String fname = editText1.getText().toString().trim();
                if (fname.length() < 3) {
                    editText1.setError("Username too short ");
                    continuebutton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String fname = editText1.getText().toString().trim();
                if (fname.length() > 3) {
                    continuebutton.setEnabled(true);

                }

            }
        });
    }*/

    private void initilize() {

        rl1 = (RelativeLayout) findViewById(R.id.imgrelative);
        toolbar = (Toolbar) findViewById(R.id.signuptoolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        imageback = (ImageView) findViewById(R.id.image1);
        imageback.setBackgroundResource(R.drawable.ic_back);
        imageback.setVisibility(View.VISIBLE);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registeruser.this, Login.class);
                startActivity(intent);
            }
        });

        textView1 = (TextView) findViewById(R.id.text1);
        textView1.setText(R.string.signup);
        textView1.setTextColor(getResources().getColor(R.color.colorBlack));
        textView1.setVisibility(View.VISIBLE);
    }


    private TextWatcher mtextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void afterTextChanged(Editable editable) {
            Checkfiledsforemptyvalues();

        }
    };

    private void Checkfiledsforemptyvalues() {

        continuebutton = (Button) findViewById(R.id.button_continue);

        String s1 = editText1.getText().toString();
        String s2 = editText2.getText().toString();
        String s3 = editText3.getText().toString();

        if (s1.length() < 4 || s2.length() < 4 || !isValidEmail(s3)) {
            continuebutton.setEnabled(false);
        } else {
            continuebutton.setEnabled(true);
        }


    }


/*
    private boolean isValidEmail(String email)
    {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }*/

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        final View currentFocus = getCurrentFocus();
        if (!(currentFocus instanceof EditText) || !isTouchInsideView(ev, currentFocus)) {
            ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return super.dispatchTouchEvent(ev);

    }

    private boolean isTouchInsideView(final MotionEvent ev, final View currentFocus) {
        final int[] loc = new int[2];
        currentFocus.getLocationOnScreen(loc);
        return ev.getRawX() > loc[0] && ev.getRawY() > loc[1] && ev.getRawX() < (loc[0] + currentFocus.getWidth())
                && ev.getRawY() < (loc[1] + currentFocus.getHeight());
    }
}

