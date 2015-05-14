package com.example.bryanty.materialdesignproject.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bryanty.materialdesignproject.JSON_key.SubjectKey;
import com.example.bryanty.materialdesignproject.R;
import com.example.bryanty.materialdesignproject.SubjectAdapter;
import com.example.bryanty.materialdesignproject.network.VolleySingleton;
import com.example.bryanty.materialdesignproject.object.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //volley initial for web services
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private static final String URL_SUBJECT_LIST="http://terminaltester.net16.net/ListSubject.php";

    //object initial (Subjects)
    private ArrayList<Subject> listSubject= new ArrayList<>();
    private RecyclerView recyclerViewSubject;

    //initial adapter
    private SubjectAdapter subjectAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment1 newInstance(String param1, String param2) {
        TabFragment1 fragment = new TabFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //volley initial
        volleySingleton= VolleySingleton.getInstance();
        requestQueue= volleySingleton.getRequestQueue();
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, URL_SUBJECT_LIST, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //toast result
                //Toast.makeText(getActivity(), "Response " + response, Toast.LENGTH_SHORT).show();

                listSubject= parseJSONResponse(response);
                subjectAdapter.setSubjectList(listSubject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

    }

    private void sendJSONRequest(){
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, URL_SUBJECT_LIST, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //toast result
                //Toast.makeText(getActivity(), "Response " + response, Toast.LENGTH_SHORT).show();

                listSubject= parseJSONResponse(response);
                subjectAdapter.setSubjectList(listSubject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }

    //parse JSON response
    public ArrayList<Subject> parseJSONResponse(JSONObject response){
       ArrayList<Subject> listSubject= new ArrayList<>();

        if(response== null || response.length()== 0){
            return null;
        }

        try {
            JSONArray arraySubject= response.getJSONArray(SubjectKey.KEY_SUBJECTS);
            StringBuilder data= new StringBuilder();

            for(int i= 0; i <arraySubject.length(); i++){
                JSONObject currentSubject= arraySubject.getJSONObject(i);
                Subject subject=new Subject();

                String id= currentSubject.getString(SubjectKey.KEY_SUBJECT_ID);
                String title= currentSubject.getString(SubjectKey.KEY_SUBJECT_NAME);

                subject.setSubjectID(id);
                subject.setSubjectTitle(title);

                listSubject.add(subject);
                data.append(id+"\n");
            }
            Toast.makeText(getActivity(), "Data \n" + listSubject.toString(), Toast.LENGTH_LONG).show();
            Log.v("log", "Data " + data.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  listSubject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
        recyclerViewSubject= (RecyclerView)view.findViewById(R.id.listSubject);
        recyclerViewSubject.setLayoutManager(new LinearLayoutManager(getActivity()));

        //set adapter
        subjectAdapter= new SubjectAdapter(getActivity());
        recyclerViewSubject.setAdapter(subjectAdapter);

        sendJSONRequest();
        return view;
    }


}
