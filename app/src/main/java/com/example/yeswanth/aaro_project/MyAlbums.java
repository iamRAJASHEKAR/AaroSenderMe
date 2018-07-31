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
public class MyAlbums extends Fragment {

    TextView textView2;

    View view;

    public MyAlbums() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_albums, container, false);

        textView2 = (TextView)view.findViewById(R.id.pairnow_myalbum);
        textView2.setTypeface(textView2.getTypeface(), Typeface.BOLD);
        textView2.setOnClickListener(pairnow);

    return view;
    }


    View.OnClickListener pairnow = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(),My_Frames_Pairing.class));
        }
    };
}
