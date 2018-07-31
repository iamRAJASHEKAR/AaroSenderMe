package com.example.yeswanth.aaro_project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import Database.DatabaseManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class SideMenuFragment extends Fragment {

    RelativeLayout layout1, layout2, layout3, layout4, layout5, layout6, relativeframe;
    ImageView cancelimage, settings_image;
    public ActionBarDrawerToggle mtoggle;
    public DrawerLayout mdrawerLayout;
    FragmentDrawerListener fragmentDrawerListener;
    TextView username,text_round;
    View view;


    public SideMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.sidemenu, container, false);
        initilizeactivitys();
        username = (TextView) view.findViewById(R.id.username_side);
        text_round = (TextView) view.findViewById(R.id.text_round);
        name();
        return view;
    }

    private void initilizeactivitys() {

        settings_image = (ImageView) view.findViewById(R.id.settings);
        settings_image.setOnClickListener(mysettings);
        layout1 = (RelativeLayout) view.findViewById(R.id.Relyt1);
        layout1.setOnClickListener(sendfiles);
        layout2 = (RelativeLayout) view.findViewById(R.id.Relyt2);
        layout2.setOnClickListener(myfiles);
        layout3 = (RelativeLayout) view.findViewById(R.id.Relyt3);
        layout3.setOnClickListener(myframes);
        layout4 = (RelativeLayout) view.findViewById(R.id.Relyt4);
        layout4.setOnClickListener(friends);
        layout5 = (RelativeLayout) view.findViewById(R.id.Relyt5);
        layout5.setOnClickListener(help);
        layout6 = (RelativeLayout) view.findViewById(R.id.Relyt6);
        layout6.setOnClickListener(about);
        cancelimage = (ImageView) view.findViewById(R.id.cancel);
        cancelimage.setOnClickListener(cancel);
        relativeframe = (RelativeLayout) view.findViewById(R.id.relative_frame);
        relativeframe.setOnClickListener(frame);

    }

    public void setFragmentDrawerListner(FragmentDrawerListener drawerListner) {
        this.fragmentDrawerListener = drawerListner;
    }


    public void setup(int fragment1, DrawerLayout drawer, final Toolbar toolbar) {

        view = getActivity().findViewById(fragment1);
        mdrawerLayout = drawer;
        mtoggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.open, R.string.close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        mdrawerLayout.setDrawerListener(mtoggle);
        mtoggle.setDrawerIndicatorEnabled(false);
        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {

            }
        });

    }


    View.OnClickListener mysettings = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (AccountSettings.check == true) {


                startActivity(new Intent(getActivity(), AccountSettings.class));
                mdrawerLayout.closeDrawers();
            } else {
                mdrawerLayout.closeDrawers();

            }

        }
    };

    View.OnClickListener cancel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mdrawerLayout.closeDrawers();


        }
    };


    View.OnClickListener frame = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // mdrawerLayout.closeDrawers();

            if (HomeScreen.check == true) {


                startActivity(new Intent(getActivity(), HomeScreen.class));
                mdrawerLayout.closeDrawers();
            } else {
                mdrawerLayout.closeDrawers();

            }

        }
    };

    View.OnClickListener sendfiles = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Clicked on Send Files", Toast.LENGTH_SHORT).show();
        }
    };


    View.OnClickListener myfiles = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // startActivity(new Intent(getActivity(),My_Files.class));
            // mdrawerLayout.closeDrawers();
            if (My_Files.check == true) {


                startActivity(new Intent(getActivity(), My_Files.class));
                mdrawerLayout.closeDrawers();
            } else {
                mdrawerLayout.closeDrawers();

            }


        }
    };

    View.OnClickListener myframes = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //   startActivity(new Intent(getActivity(), My_Frames.class));
            //  mdrawerLayout.closeDrawers();
            if (My_Frames.check == true) {


                startActivity(new Intent(getActivity(), My_Frames.class));
                mdrawerLayout.closeDrawers();
            } else {
                mdrawerLayout.closeDrawers();

            }

        }
    };

    View.OnClickListener friends = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // startActivity(new Intent(getActivity(),Friends.class));
            //   mdrawerLayout.closeDrawers();
            if (Friends.check == true) {


                startActivity(new Intent(getActivity(), Friends.class));
                mdrawerLayout.closeDrawers();
            } else {
                mdrawerLayout.closeDrawers();

            }
        }
    };

    View.OnClickListener help = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Clicked on Help", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener about = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(getContext(),About_screen.class);
            startActivity(intent);
            mdrawerLayout.closeDrawers();
            onDestroyView();*/
            if (About_screen.check == true) {


                startActivity(new Intent(getActivity(), About_screen.class));
                mdrawerLayout.closeDrawers();
            } else {
                mdrawerLayout.closeDrawers();

            }

        }
    };


    public interface FragmentDrawerListener {
    }

    public void name() {

        DatabaseManager.getInstance().getalluser().size();
        int ss = DatabaseManager.getInstance().getalluser().size();
        Log.e("logger", String.valueOf(ss));
        if (ss > 0) {

            String firstname = DatabaseManager.getInstance().currentUser.getFirstname();
            String lastname = DatabaseManager.getInstance().currentUser.getLastname();
            Log.e("deails", firstname + lastname);
            // String test = "StackOverflow";

            String fisrchar = firstname;
            String lastchar = lastname;
            char first = fisrchar.charAt(0);
            char last = lastchar.charAt(0);
            Log.e("repu", String.valueOf(first));

            text_round.setText(first+""+last);
            username.setText(firstname + "  " + lastname);
        }
    }
}
