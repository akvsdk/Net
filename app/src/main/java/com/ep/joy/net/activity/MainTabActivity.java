package com.ep.joy.net.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ep.joy.net.R;
import com.ep.joy.net.base.BaseActivity;
import com.ep.joy.net.fragment.FragmentFour;
import com.ep.joy.net.fragment.FragmentOne;
import com.ep.joy.net.fragment.FragmentThree;
import com.ep.joy.net.fragment.FragmentTwo;


public class MainTabActivity extends BaseActivity {

    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabFragmentPagerAdapter mAdapter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main_tab;
    }

    @Override
    protected void initViewsAndEvents() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mTabLayout = (TabLayout) findViewById(android.R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setSupportActionBar(mToolBar);
        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private static class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        private String tabTitles[] = new String[]{"首页", "发现", "我的", "设置"};

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
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
