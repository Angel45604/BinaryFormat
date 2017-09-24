package com.marcos.angel.binaryformat.fragmentPagerAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.marcos.angel.binaryformat.fragment.binary.Section1Fragment;
import com.marcos.angel.binaryformat.fragment.feistel.Section2Fragment;
import com.marcos.angel.binaryformat.fragment.DES.Section3Fragment;

/**
 * Created by angel on 03/09/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int sectionCount;
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, int seccionCount, Context context) {
        super(fm);
        this.context = context;
        this.sectionCount=seccionCount;

    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new Section1Fragment();
            case 1:
                return new Section2Fragment();
            case 2:
                return new Section3Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return sectionCount;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Binario";
            case 1:
                return "Feistel";
            case 2:
                return "DES";
            default:
                return null;
        }
    }
}
