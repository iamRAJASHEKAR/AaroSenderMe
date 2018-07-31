package com.example.yeswanth.aaro_project;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class My_Files extends AppCompatActivity implements SideMenuFragment.FragmentDrawerListener {



    public static boolean check = true;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView image1;
    RelativeLayout rl1;
    TextView textView1;
    Toolbar toolbar;
    DrawerLayout drawer;
    private SideMenuFragment sideMenuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__files);

        check = false;
        viewPager=(ViewPager)findViewById(R.id.viewpager1);
        createViewpager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout1);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons();

        initilize();
        initilizedrawer();
    }

    private void createViewpager(ViewPager viewPager) {

        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MyPlaylist(), "Tab 1");
        adapter.addFrag(new MyAlbums(), "Tab 2");
        viewPager.setAdapter(adapter);
        viewPager.beginFakeDrag();


    }

    private void createTabIcons() {

        TextView tabone = (TextView) LayoutInflater.from(this)
                .inflate(R.layout.custom_tab,null);
        tabone.setText("My Playlists");

        tabone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_playlist,0,0,0);
        tabLayout.getTabAt(0).setCustomView(tabone);


        TextView tabtwo = (TextView) LayoutInflater.from(this)
                .inflate(R.layout.custom_tab,null);
        tabtwo.setText("My Albums");
        tabtwo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_image,0,0,0);
        tabLayout.getTabAt(1).setCustomView(tabtwo);

    }

    class ViewpagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void initilize() {
        rl1 = (RelativeLayout)findViewById(R.id.imgrelative);

        image1 = (ImageView)findViewById(R.id.image1);
        image1.setBackgroundResource(R.drawable.ic_menu);
        image1.setVisibility(View.VISIBLE);

        textView1 = (TextView)findViewById(R.id.text1);
        textView1.setText(R.string.myfiles);
        textView1.setVisibility(View.VISIBLE);
    }
    private void initilizedrawer(){

        drawer = (DrawerLayout)findViewById(R.id.myfilesdrawerlayout);
        sideMenuFragment = (SideMenuFragment)getSupportFragmentManager().findFragmentById(R.id.fragment1);
        sideMenuFragment.setup(R.id.fragment1,drawer,toolbar);
        sideMenuFragment.setFragmentDrawerListner(this);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        check = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        check = true;
    }
}
