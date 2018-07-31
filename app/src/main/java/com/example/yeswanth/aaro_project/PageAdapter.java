package com.example.yeswanth.aaro_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by yeswanth on 1/25/2018.
 */

class PageAdapter extends FragmentStatePagerAdapter {


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MyPlaylist();
                break;
            case 1 :
                fragment = new MyAlbums();
                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
