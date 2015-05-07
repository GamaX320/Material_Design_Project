package com.example.bryanty.materialdesignproject.tabs;

import android.app.DownloadManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
        Bundle bundle= getArguments();
        if(bundle!= null){
            textView.setText("The page selected is "+ bundle.getInt("position"));

        }

        //volley request
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        //StringRequest( Request method, Url, Response, Error)
        StringRequest request= new StringRequest(Request.Method.GET, "http://www.google.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //get some data and response
                //Toast.makeText(getActivity(), "Response "+response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //if any error will come this block
                //Toast.makeText(getActivity(), "ERROR "+error, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);

        return view;
    }
}
