package com.example.yeswanth.aaro_project;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

public class Feedback extends AppCompatActivity {


    ImageView image1;
    RelativeLayout rl1;
    TextView textView1;
    SimpleRatingBar feedback;
    EditText feedbacktext;
    Button sendbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedback =(SimpleRatingBar)findViewById(R.id.ratingBar3);
        feedback.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(SimpleRatingBar simpleRatingBar, float rating, boolean fromUser) {
                Toast.makeText(Feedback.this,String.valueOf(feedback.getRating())
                        ,Toast.LENGTH_SHORT).show();
            }
        });

        feedbacktext = (EditText)findViewById(R.id.feedbackform);
        sendbutton = (Button)findViewById(R.id.sendfeedbackbutton);
        sendbutton.setOnClickListener(sendfeedback);


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
                Intent intent = new Intent(Feedback.this,About_screen.class);
                startActivity(intent);
                finish();
                onBackPressed();
            }
        });

        textView1 = (TextView)findViewById(R.id.text1);
        textView1.setText(R.string.feedback);
        textView1.setVisibility(View.VISIBLE);
    }

    View.OnClickListener sendfeedback = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/html");
            i.putExtra(Intent.EXTRA_EMAIL,new String[]{"davara.yaswanth@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT,"Feedback from App");
            i.putExtra(Intent.EXTRA_TEXT,"Rating : "+feedback.getRating()+
            "\nMessage : "+feedbacktext.getText());

            try {
                startActivity(Intent.createChooser(i,"send feedback...."));

            }catch (ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(),"there are noemailclients",
                        Toast.LENGTH_SHORT).show();

            }

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
