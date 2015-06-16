package com.example.bryanty.materialdesignproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.bryanty.materialdesignproject.tabs.TabFragment;
import com.example.bryanty.materialdesignproject.tabs.TabsActivity;
import com.example.bryanty.materialdesignproject.tabs_with_library.TabsWithLibraryActivity;
import com.example.bryanty.materialdesignproject.textfield_with_library.TextFieldActivity;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add custom toolbar
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //add navigation drawer
        NavigationDrawerFragment drawerFragment= (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout), toolbar, R.id.fragment_navigation_drawer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if(id == R.id.action_next){
            //startActivity(new Intent(this, MyActivity.class));
            startActivity(new Intent(this, TabsActivity.class));
        }
        else if(id == R.id.action_tabs_with_library){
            startActivity(new Intent(this, TabsWithLibraryActivity.class));
        }
        else if(id == R.id.action_vector_test){
            startActivity(new Intent(this, VectorTestActivity.class));
        }
        else if(id == R.id.action_material_texfield){
            startActivity(new Intent(this, TextFieldActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    //pager adapter
    class MyPagerAdapter extends FragmentPagerAdapter {

        int icons[]= {R.mipmap.ic_next, R.mipmap.ic_test, R.mipmap.ic_test2};
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
        public int getCount() {
            return 3;
        }
    }
}
