package com.example.yeswanth.aaro_project;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPlaylist extends Fragment {


    TextView textView1;
    View view;
    public MyPlaylist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_playlist, container, false);

        textView1 =(TextView)view.findViewById(R.id.pairnow_myplaylist);
        textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD);
        textView1.setOnClickListener(pairnow);

        return view;
    }

    View.OnClickListener pairnow = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(),My_Frames_Pairing.class));
        }
    };


}
