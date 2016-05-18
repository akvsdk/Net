package com.ep.joy.net.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ep.joy.net.R;
import com.ep.joy.net.fragment.FragmentFour;
import com.ep.joy.net.fragment.FragmentOne;
import com.ep.joy.net.fragment.FragmentThree;
import com.ep.joy.net.fragment.FragmentTwo;


/**
 * 类描述:
 * 创建人:malin
 * 创建时间:2016 16-4-8 17:36
 * 备注:{@link }
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
public class MainTabActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mTabLayout = (TabLayout) findViewById(android.R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setSupportActionBar(mToolBar);
        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }


    private static class TabFragmentPagerAdapter extends FragmentPagerAdapter {


        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return FragmentOne.newInstance(0);
                }
                case 1: {
                    return FragmentTwo.newInstance(1);
                }
                case 2: {
                    return FragmentThree.newInstance(2);
                }
                case 3: {
                    return FragmentFour.newInstance(3);
                }

                default: {
                    return FragmentOne.newInstance(0);
                }
            }
        }

        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: {
                    return "0";
                }
                case 1: {
                    return "1";
                }
                case 2: {
                    return "2";
                }
                case 3: {
                    return "3";
                }

                default: {
                    return "0";
                }
            }
        }
    }
}
