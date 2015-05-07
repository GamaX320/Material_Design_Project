package com.example.bryanty.materialdesignproject.tabs;


import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.bryanty.materialdesignproject.R;

public class TabsActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        //add custom toolbar
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //add tabs layout
        mPager= (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs= (SlidingTabLayout)findViewById(R.id.tabs);
        //set custom tab view
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        //set slider background color and selected color to accent color
        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));

        mTabs.setViewPager(mPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //pager adapter
    class MyPagerAdapter extends FragmentPagerAdapter {

        int icons[]= {R.mipmap.ic_test, R.mipmap.ic_test, R.mipmap.ic_test2};
        String[] tabText= {"Tab 1","Tab 2","Tab 3"};

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            TabFragment tabFragment= TabFragment.getInstance(position);
            return tabFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //icon
            Drawable drawable= getResources().getDrawable(icons[position]);
            //drawable.setBounds(0,0,36,36);
            drawable.setBounds(0,0,72,72);

            //imagespan is something combine text and image together
            ImageSpan imageSpan= new ImageSpan(drawable);
            SpannableString spannableString= new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannableString;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
