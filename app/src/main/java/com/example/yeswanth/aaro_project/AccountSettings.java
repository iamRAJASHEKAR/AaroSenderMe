package com.example.yeswanth.aaro_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Database.DatabaseManager;
import FONTS.ButtonFontStyle;
import Model.Userdata;
import Serverinterface.ServerinterfaceApi;
import Sessions.SessionsManager;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import serverobjects.RegistrationServerojects;

public class AccountSettings extends AppCompatActivity implements SideMenuFragment.FragmentDrawerListener {
    private SessionsManager sessions;

    ImageView image1;
    public static boolean check = true;
    RelativeLayout rl1;

    CheckBox checkBox_current, checkBox_new, checkBox_autoaccept, checkBox_optimize_photo;
    TextView textView1, logout, username_settings, username, usermail;
    Toolbar toolbar;
    public ArrayList<Userdata> userlist;
    Userdata userdata;
    EditText current_pass, new_pass;
    ButtonFontStyle buttonFontStyle;
    DrawerLayout drawer;
    private SideMenuFragment sideMenuFragment;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        checkBox_current = (CheckBox) findViewById(R.id.checkbox_curepass);
        checkBox_optimize_photo = (CheckBox) findViewById(R.id.checkbox_optimizephoto);
        checkBox_autoaccept = (CheckBox) findViewById(R.id.checkbox_autoaccpet);
        checkBox_new = (CheckBox) findViewById(R.id.checkbox_newpass);
        username_settings = (TextView) findViewById(R.id.username_settings);
        username = (TextView) findViewById(R.id.username);
        usermail = (TextView) findViewById(R.id.user_mail);
        current_pass = (EditText) findViewById(R.id.editpass_account);
        new_pass = (EditText) findViewById(R.id.editconfpass_account);
        buttonFontStyle = (ButtonFontStyle) findViewById(R.id.changepswd);

        userdata = new Userdata();
        userlist = new ArrayList<>();
        Checkfiledsforemptyvalues();

        current_pass.addTextChangedListener(mtextWatcher);
        new_pass.addTextChangedListener(mtextWatcher);


        checkBox_current.setOnClickListener(check_current);
        checkBox_new.setOnClickListener(check_new);

        sessions = new SessionsManager(getApplicationContext());
        check = false;
        initilize();
        initilizedrawer();
        userdata_from_orm();
    }

    View.OnClickListener check_current = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String cupass = current_pass.getText().toString().trim();
            if (checkBox_current.isChecked()) {
                current_pass.setTransformationMethod(null);

                // Toast.makeText(AccountSettings.this, "checked", Toast.LENGTH_SHORT).show();
            } else {
                current_pass.setTransformationMethod(new PasswordTransformationMethod());
            }
        }
    };


    View.OnClickListener check_new = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String cupass = current_pass.getText().toString().trim();
            if (checkBox_new.isChecked()) {
                new_pass.setTransformationMethod(null);

                // Toast.makeText(AccountSettings.this, "checked", Toast.LENGTH_SHORT).show();
            } else {
                new_pass.setTransformationMethod(new PasswordTransformationMethod());
            }
        }
    };


    private void initilize() {

        rl1 = (RelativeLayout) findViewById(R.id.imgrelative);

        image1 = (ImageView) findViewById(R.id.image1);
        image1.setBackgroundResource(R.drawable.ic_menu);
        image1.setVisibility(View.VISIBLE);

        textView1 = (TextView) findViewById(R.id.text1);
        logout = (TextView) findViewById(R.id.text_tool);
        logout.setText(R.string.signout);
        logout.setTypeface(logout.getTypeface(), Typeface.BOLD);

        textView1.setText(R.string.account_settings);
        textView1.setVisibility(View.VISIBLE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlist = DatabaseManager.getInstance().getalluser();
                sessions.setLogin(false);
                Log.e("hoooo", String.valueOf(userlist.size()));
                DatabaseManager.getInstance().delete_allusers(userlist);
                Log.e("delete", "mama deleted");
                if (sessions.isLoggedIn()) {

                }

                startActivity(new Intent(AccountSettings.this, Login.class));
                finish();


            }
        });
    }

    private void initilizedrawer() {
        drawer = (DrawerLayout) findViewById(R.id.account_drawerlayout);
        sideMenuFragment = (SideMenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        sideMenuFragment.setup(R.id.fragment1, drawer, toolbar);
        sideMenuFragment.setFragmentDrawerListner(this);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
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
        String s1 = current_pass.getText().toString();
        String s2 = new_pass.getText().toString();
        if (s1.equals("") || s2.equals("")) {
            buttonFontStyle.setEnabled(false);
        } else {
            buttonFontStyle.setEnabled(true);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        check = true;
        // Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();

        updater();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        check = true;
        updater();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void userdata_from_orm() {

        DatabaseManager.getInstance().getalluser().size();
        int ss = DatabaseManager.getInstance().getalluser().size();
        Log.e("logger", String.valueOf(ss));
        if (ss > 0) {

            String firstname = DatabaseManager.getInstance().currentUser.getFirstname();
            String lastname = DatabaseManager.getInstance().currentUser.getLastname();
            String user_mail = DatabaseManager.getInstance().currentUser.getEmail();
            boolean auto_accept = DatabaseManager.getInstance().currentUser.isAutoaccept_friend();
            boolean photo_optimize = DatabaseManager.getInstance().currentUser.isOptimize_photos();

            Log.e("deails", String.valueOf(auto_accept));
            // String test = "StackOverflow";
            String fisrchar = firstname;
            String lastchar = lastname;
            char first = fisrchar.charAt(0);
            char last = lastchar.charAt(0);
            Log.e("repu", String.valueOf(first));

            username_settings.setText(first + "" + last);
            usermail.setText(user_mail);
            username.setText(firstname + "  " + lastname);
            if (auto_accept == true) {
                checkBox_autoaccept.setChecked(true);
            } else {
                checkBox_autoaccept.setChecked(false);
            }
            if (photo_optimize == true) {
                checkBox_optimize_photo.setChecked(true);

            } else checkBox_optimize_photo.setChecked(false);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updater();
    }

    public boolean compare_auto() {
        if (checkBox_autoaccept.isChecked()) {
            return true;
        } else return false;
    }

    public boolean compare_photo() {
        if (checkBox_optimize_photo.isChecked()) {
            return true;
        } else return false;

    }

    public void updater() {
        String firstname = DatabaseManager.getInstance().currentUser.getFirstname();
        String lastname = DatabaseManager.getInstance().currentUser.getLastname();
        String user_mail = DatabaseManager.getInstance().currentUser.getEmail();
        String user_name = DatabaseManager.getInstance().currentUser.getUsername();
        String user_pass = DatabaseManager.getInstance().currentUser.getPassword();
        String user_country = DatabaseManager.getInstance().currentUser.getCountry();
        boolean auto_accept = DatabaseManager.getInstance().currentUser.isAutoaccept_friend();
        boolean photo_optimize = DatabaseManager.getInstance().currentUser.isOptimize_photos();

        boolean accept = compare_auto();

        boolean photo = compare_photo();
        if (auto_accept != accept || photo_optimize != compare_photo()) {
            DatabaseManager.getInstance().update_user(user_name, user_pass, firstname, lastname, user_country, user_mail, photo, accept);
            // Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
    }

    private void changepassword(final String user, final String oldpswd, final String newpswd) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();

        JSONObject changepass = new JSONObject();

        try {
            changepass.put("UserName", user);
            changepass.put("Password", oldpswd);
            changepass.put("NewPassword", newpswd);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerinterfaceApi.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerinterfaceApi api = retrofit.create(ServerinterfaceApi.class);
        Call<RegistrationServerojects> changepsd = api.changepassword(changepass);
        changepsd.enqueue(new retrofit2.Callback<RegistrationServerojects>() {
            @Override
            public void onResponse(Call<RegistrationServerojects> call, Response<RegistrationServerojects> response) {

                progressDialog.dismiss();
                String status = response.body().getResponse();
                String message = response.body().getMessage();

                Log.e("changepsw", status + message);
                Toast.makeText(AccountSettings.this, status + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegistrationServerojects> call, Throwable t) {
                Log.e("failed", "failed to changepassword");
            }
        });
    }
}
