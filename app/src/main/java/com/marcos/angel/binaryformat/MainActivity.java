package com.marcos.angel.binaryformat;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.marcos.angel.binaryformat.fragment.Section1Fragment;
import com.marcos.angel.binaryformat.fragmentPagerAdapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements Section1Fragment.OnFragmentInteractionListener{

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.MainTabLayout);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle("");

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager = (ViewPager) findViewById(R.id.MainViewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),this);

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
