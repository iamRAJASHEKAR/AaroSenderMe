package com.example.yeswanth.aaro_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.DatabaseManager;
import Model.Userdata;
import Serverinterface.ServerinterfaceApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import serverobjects.RegistrationServerojects;

/**
 * Created by ravali on 1/24/2018.
 */

public class signin_cancel extends RemainderSent {
    Button button_signup, cancel, backbutton;
    EditText username, password;
    CheckBox checkBox;
    Toolbar toolbar;
    String firstname, lastname, mail, countryname, uname, pswd;
    ImageView signincancelimg1;
    TextView signincanceltxt1, signincanceltxt2;
    Userdata userdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_cancel);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);

        Intent intent = getIntent();
        firstname = intent.getStringExtra("first_name");
        lastname = intent.getStringExtra("last_name");
        mail = intent.getStringExtra("mail");
        countryname = intent.getStringExtra("country");

        Log.e("message", mail);


        initilize();

        userdata = new Userdata();
        checkBox = (CheckBox) findViewById(R.id.checkbox_login);

        //    cancel = (Button) findViewById(R.id.cancel);
        //    backbutton = (Button) findViewById(R.id.backbutton);
        button_signup = (Button) findViewById(R.id.button__signup);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


        Checkfiledsforemptyvalues();
        username.addTextChangedListener(mtextWatcher);
        password.addTextChangedListener(mtextWatcher);


      /*  cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signin_cancel.this, Login.class);
                startActivity(intent);
            }
        });*/
        //     textla();
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = username.getText().toString().trim();
                pswd = password.getText().toString().trim();
                registrationData(firstname, lastname, mail, countryname, uname, pswd);
                boolean photo = false;
                boolean auto_accept = false;
                // Toast.makeText(getApplicationContext(),firstname + "\n" + lastname + "\n" + mail + "\n" + countryname + "\n" + uname + "\n" + pswd + "\n" + photo + "\n" + auto_accept, Toast.LENGTH_SHORT).show();
                Log.e("mineeeeee", firstname + "\n" + lastname + "\n" + mail + "\n" + countryname + "\n" + uname + "\n" + pswd + "\n" + photo + "\n" + auto_accept);
                register_orm(firstname, lastname, mail, countryname, uname, pswd, photo, auto_accept);

                String ss = String.valueOf(DatabaseManager.getInstance().getalluser().size());

                button_signup.setEnabled(false);
                Log.e("raju", ss);
                if (!ss.equals("0")) {
                    Intent in = new Intent(getBaseContext(), Congrats.class);
                    startActivity(in);
                    finish();
                }
            }


            private boolean isValidPassword(String pass) {
                if (pass != null && pass.length() > 4) {
                    return true;
                }
                return false;
            }

            private boolean isValidEmail(String email) {

                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }
        });

     /*   backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signin_cancel.this, RemainderSent.class);
                startActivity(intent);
            }
        });*/


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker();
            }
        });

        //checker();
    }


    public void checker() {
        if (checkBox.isChecked()) {
            password.setTransformationMethod(null);

        } else password.setTransformationMethod(new PasswordTransformationMethod());
    }


    private void initilize() {

        rl1 = (RelativeLayout) findViewById(R.id.imgrelative);
        toolbar = (Toolbar) findViewById(R.id.signincanceltoolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));


        signincancelimg1 = (ImageView) findViewById(R.id.image1);
        signincancelimg1.setBackgroundResource(R.drawable.ic_back);
        signincancelimg1.setVisibility(View.VISIBLE);
        signincancelimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signin_cancel.this, Registeruser.class);
                startActivity(intent);
            }
        });

        signincanceltxt1 = (TextView) findViewById(R.id.text1);
        signincanceltxt1.setText(R.string.signup);
        signincanceltxt1.setTextColor(getResources().getColor(R.color.colorBlack));
        signincanceltxt1.setVisibility(View.VISIBLE);

        signincanceltxt2 = (TextView) findViewById(R.id.text_tool);
        signincanceltxt2.setText(R.string.cancel);
        signincanceltxt2.setTextColor(getResources().getColor(R.color.colorBrown));
        signincanceltxt2.setTypeface(signincanceltxt2.getTypeface(), Typeface.BOLD);

        signincanceltxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signin_cancel.this, Login.class);
                startActivity(intent);
            }
        });
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

        // button__signin = (Button) findViewById(R.id.button1);

        String s1 = username.getText().toString();
        String s2 = password.getText().toString();


        if (s1.equals("") || s2.equals("")) {
            button_signup.setEnabled(false);
        } else {
            button_signup.setEnabled(true);
        }


    }


    //////////////////////////     Registration Api     /////////////////////////

    private void registrationData(final String fname, final String lname, final String mail, final String countryname,
                                  final String uname, final String passwd) {
        JSONObject registerobj = new JSONObject();
        try {
            registerobj.put("FirstName", fname);
            registerobj.put("LastName", lname);
            registerobj.put("EmailAddress", mail);
            registerobj.put("Password", passwd);
            registerobj.put("Country", countryname);
            registerobj.put("UserName", uname);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerinterfaceApi.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerinterfaceApi api = retrofit.create(ServerinterfaceApi.class);
        Call<RegistrationServerojects> calling = api.regdetails(registerobj);
        Log.e("call", "register");
        Log.e("reg detailes", registerobj.toString());
        calling.enqueue(new retrofit2.Callback<RegistrationServerojects>() {
            @Override
            public void onResponse(Call<RegistrationServerojects> call, Response<RegistrationServerojects> response) {

                String status = response.body().getResponse();
                Log.e("ashok", status);
                String msg = response.body().getMessage();
                Log.e("status", "" + status);
                Log.e("msg", msg);
                if (status.equals("3")) {
                    Toast.makeText(signin_cancel.this, msg, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else if (status.equals("2"))

                {
                    Toast.makeText(signin_cancel.this, "chudali mama issue", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegistrationServerojects> call, Throwable t) {
                Log.e("failed", "failed to register");
            }
        });
    }

    public void register_orm(final String fname, final String lname, final String mail, final String countryname,
                             final String uname, final String passwd, boolean photo, boolean auto_accept) {
        userdata.setFirstname(fname);
        userdata.setLastname(lname);
        userdata.setEmail(mail);
        userdata.setCountry(countryname);
        userdata.setUsername(uname);
        userdata.setPassword(passwd);
        userdata.setOptimize_photos(photo);
        userdata.setAutoaccept_friend(auto_accept);

        DatabaseManager.getInstance().add_user(userdata);
        Log.e("register_orm", "employess added sucessfully");


    }

}






