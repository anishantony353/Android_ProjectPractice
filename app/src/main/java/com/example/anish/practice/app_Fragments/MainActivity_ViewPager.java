package com.example.anish.practice.app_Fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Fragments.fragments.Fragment_tab_a;
import com.example.anish.practice.app_Fragments.fragments.Fragment_tab_b;
import com.example.anish.practice.app_Fragments.fragments.Fragment_tab_c;

public class MainActivity_ViewPager extends AppCompatActivity {
    ViewPager pager;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__fragment_tabs);
        pager = (ViewPager)findViewById(R.id.VP_fragmentActivity);
        pager.setAdapter(new PagerAdaper(getSupportFragmentManager()));
        setUpActionBar();


    }

    private void setUpActionBar() {
        actionBar = getActionBar();
        //actionBar.setNavigationMode();
    }

    class PagerAdaper extends FragmentStatePagerAdapter {
        Fragment frag = null;

        public PagerAdaper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    frag = new Fragment_tab_a();
                    break;
                case 1:
                    frag = new Fragment_tab_b();
                    break;
                case 2:
                    frag = new Fragment_tab_c();
                    break;
            }

            return frag;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title="";
            switch(position){
                case 0:
                    title = "tab A";
                    break;
                case 1:
                    title = "tab B";
                    break;
                case 2:
                    title = "tab C";
                    break;
            }
            return title;
        }
    }
}
