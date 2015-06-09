package com.example.bryanty.materialdesignproject.tabs_with_library;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bryanty.materialdesignproject.R;
import com.example.bryanty.materialdesignproject.tabs.TabFragment;
import com.example.bryanty.materialdesignproject.tabs.TabFragment1;
import com.example.bryanty.materialdesignproject.tabs.TabFragment2;
import com.example.bryanty.materialdesignproject.tabs.TabFragment3;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class TabsWithLibraryActivity extends ActionBarActivity implements MaterialTabListener{

    private Toolbar toolbar;
    private ViewPager viewPager;
    private MaterialTabHost tabHost;

    //fragment position
    public static final int DEFAULT_FRAGMENT= 0;
    public static final int FIRST_FRAGMENT= 1;
    public static final int SECOND_FRAGMENT= 2;
    public static final int THRID_FRAGMENT=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_with_library);

        //add custom toolbar
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabHost= (MaterialTabHost)findViewById(R.id.materialTabHost);
        viewPager= (ViewPager)findViewById(R.id.viewPager);

        // init view pager
        MyPagerAdapter adapter= new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs_with_library, menu);
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

    //custom MaterialTabHost implement class
    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    //custom MaterialTabHost implement class
    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    //custom MaterialTabHost implement class
    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    //pager adapter
    class MyPagerAdapter extends FragmentPagerAdapter {

        int icons[]= {R.mipmap.ic_test, R.mipmap.ic_test, R.mipmap.ic_test2};
        String[] tabText= {"Tab 1","Tab 2","Tab 3","Tab 4"};

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            TabFragment tabFragment= TabFragment.getInstance(position);

            Fragment fragment= null;
            switch(position){
                case DEFAULT_FRAGMENT:
                    fragment= TabFragment1.newInstance("", "");
                    break;
                case FIRST_FRAGMENT:
                    fragment= TabFragment1.newInstance("","");
                    break;
                case SECOND_FRAGMENT:
                    fragment= TabFragment2.newInstance("", "");
                    break;
                case THRID_FRAGMENT:
                    fragment= TabFragment3.newInstance("", "");
                    break;

            }
            //return tabFragment;
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            //icon
//            Drawable drawable= getResources().getDrawable(icons[position]);
//            //drawable.setBounds(0,0,36,36);
//            drawable.setBounds(0,0,72,72);
//
//            //imagespan is something combine text and image together
//            ImageSpan imageSpan= new ImageSpan(drawable);
//            SpannableString spannableString= new SpannableString(" ");
//            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            return spannableString;
            return tabText[position];
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
