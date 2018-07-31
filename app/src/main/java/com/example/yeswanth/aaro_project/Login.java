package com.example.yeswanth.aaro_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.DatabaseManager;
import Serverinterface.ServerinterfaceApi;
import Sessions.SessionsManager;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import serverobjects.RegistrationServerojects;

/**
 * Created by ravali on 1/23/2018.
 */

public class Login extends AppCompatActivity {
    Button button__signin;
    CheckBox checkBox;
    TextView forgot_password, create_account;
    EditText username, password;

    private SessionsManager sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);

        sessions = new SessionsManager(getApplicationContext());
        checkBox = (CheckBox) findViewById(R.id.checkbox_login);
        button__signin = (Button) findViewById(R.id.button__signin);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        DatabaseManager.getInstance().fillContext(getApplicationContext());
        Checkfiledsforemptyvalues();
        checkconnection();

        username.addTextChangedListener(mtextWatcher);
        password.addTextChangedListener(mtextWatcher);


        create_account = (TextView) findViewById(R.id.create_account);
        create_account.setTypeface(create_account.getTypeface(), Typeface.BOLD);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registeruser.class);
                startActivity(intent);
            }
        });
        //textla();
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        forgot_password.setTypeface(forgot_password.getTypeface(), Typeface.BOLD);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Remainder.class);
                startActivity(i);
                //finish();
            }
        });

        if (sessions.isLoggedIn()) {
            Intent i = new Intent(getApplicationContext(), HomeScreen.class);
            startActivity(i);
            finish();

        }

        button__signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataon()) {
                    String user = username.getText().toString().trim();
                    String pass = password.getText().toString().trim();
                    registrationData(user, pass);


                } else {
                    Toast.makeText(getApplicationContext(), "internet is not connected", Toast.LENGTH_SHORT).show();

                }

               /* final String email = username.getText().toString();

                final String pass = password.getText().toString();

                *//*if (!isValidEmail(email)) {

                    username.setError("Invalid Email");

                } else*//* if (!isValidPassword(pass)) {

                    password.setError("Invalid Password");
               // Toast.makeText(getApplicationContext(),"invalid Password",
                 //       Toast.LENGTH_SHORT).show();

                } else {


                    Intent i = new Intent(Login.this, HomeScreen.class);
                    startActivity(i);
                    finish();
                }*/
            }


            private boolean isValidPassword(String pass) {
                if (pass != null && pass.length() > 6) {
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

/*    public void textla() {
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String fname = username.getText().toString().trim();
                if (fname.equals("")) {
                    username.setError("Username not to be Empty");
                    button__signin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String fname = username.getText().toString().trim();
                if (fname.length()>1) {
                    button__signin.setEnabled(true);

                }

            }
        });

    }*/


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
            button__signin.setEnabled(false);
        } else {
            button__signin.setEnabled(true);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public Boolean dataon() {

        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void checkconnection() {
        if (dataon()) {
            Toast.makeText(getApplicationContext(), "internet is connected", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getApplicationContext(), "please turn on internet", Toast.LENGTH_SHORT).show();
        }
    }

    //////////////////////////     Registration Api     /////////////////////////

    private void registrationData(final String user, final String pass) {
        JSONObject login = new JSONObject();
        try {
            login.put("UserName", user);
            login.put("Password", pass);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerinterfaceApi.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerinterfaceApi api = retrofit.create(ServerinterfaceApi.class);
        Call<RegistrationServerojects> calling = api.logindetails(login);
        Log.e("call", "register");
        Log.e("reg detailes", login.toString());
        calling.enqueue(new retrofit2.Callback<RegistrationServerojects>() {
            @Override
            public void onResponse(Call<RegistrationServerojects> call, Response<RegistrationServerojects> response) {

                String status = response.body().getResponse();
                String minemsg = response.body().getMessage();
                Log.e("login", status + minemsg);
                Toast.makeText(Login.this, status + minemsg, Toast.LENGTH_SHORT).show();

                if (status.equals("3"))
                {
                    Toast.makeText(Login.this, status + minemsg, Toast.LENGTH_SHORT).show();
                    sessions.setLogin(true);
                    Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                    startActivity(i);
                    finish();
                }
             /*   Log.e("ashok", status);
                String msg = response.body().getMessage();
                Log.e("status", "" + status);
                Log.e("msg", msg);
                if (status.equals("3")) {
                    Toast.makeText(Login.this, msg, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else if (status.equals("2")) {
                    Toast.makeText(Login.this, "chudali mama issue", Toast.LENGTH_LONG).show();
                }
*/
            }

            @Override
            public void onFailure(Call<RegistrationServerojects> call, Throwable t) {
                Log.e("failed", "failed to register");
            }
        });
    }


}





