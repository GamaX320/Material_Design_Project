package com.example.bryanty.materialdesignproject;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements DrawerItemAdapter.ClickListener{

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    public static final String PREF_FILE_NAME= "testpref";
    public static final String KEY_USER_LEARNED_DRAWER= "user_learned_drawer";

    //initial recyclerView
    private RecyclerView recyclerView;

    //initial drawer item
    private DrawerItemAdapter adapter;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        //find recyclerView view id
        recyclerView= (RecyclerView) view.findViewById(R.id.drawerList);

        //drawer item
        adapter= new DrawerItemAdapter(getActivity(), getData());
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), "onClick " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "onLongClick " + position, Toast.LENGTH_SHORT).show();
            }
        }));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer= Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));

        if(savedInstanceState!= null){
            mFromSavedInstanceState= true;
        }
    }

    //navigation drawer setup
    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar, int fragmentID){
        containerView= getActivity().findViewById(fragmentID);
        mDrawerLayout= drawerLayout;
        mDrawerToggle= new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer= true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu();
            }
        };

        //first time user launch the app the navigation drawer will show up to let people know this app got navigation drawer
        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                //navigation drawer indicator
                mDrawerToggle.syncState();

            }
        });
    }

    //get drawer item
    public static List<DrawerItem> getData(){
        List<DrawerItem> data= new ArrayList<>();

        data.add(new DrawerItem(R.mipmap.ic_test,"Dummy 1"));
        data.add(new DrawerItem(R.mipmap.ic_test2,"Dummy 2"));
        data.add(new DrawerItem(R.mipmap.ic_next,"Dummy 3"));

        return data;
    }

    //save user navigation preference
    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences= context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply(); //can be used apply() or commint() but apply() will be more fast
    }

    //read user navigation preference
    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences= context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    //interface implement method to navigation drawer item clicked
    @Override
    public void itemClicked(View view, int position) {
        startActivity(new Intent(getActivity(), MyActivity.class));
    }

    //recyclerview onItemTouch listener
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        //using supplied montion event
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            Log.v("myActivity", "you are on RecyclerTouchListener");
            this.clickListener= clickListener;
            gestureDetector= new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.v("myActivity", "you are on onSingleTapUp "+e);
                    //return super.onSingleTapUp(e);
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    Log.v("myActivity", "you are on onLongPress "+e);
                    View child= recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if(child!= null && clickListener!= null){
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                    //super.onLongPress(e);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child= rv.findChildViewUnder(e.getX(), e.getY());

            if(child!= null && clickListener!= null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.v("myActivity", "you are on onTouchEvent "+ e);

        }
    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }
}
