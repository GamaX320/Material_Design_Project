package com.example.bryanty.materialdesignproject.tabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bryanty.materialdesignproject.R;

import org.w3c.dom.Text;

/**
 * Created by BRYANTY on 06/05/2015.
 */
public  class TabFragment extends Fragment{

    private TextView textView;

    public static TabFragment getInstance(int position){
        TabFragment myFragment= new TabFragment();
        Bundle args= new Bundle();
        args.putInt("position", position);
        myFragment.setArguments(args)   ;

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab,container, false);
        textView= (TextView)view.findViewById(R.id.position);
        return view;
    }
}
