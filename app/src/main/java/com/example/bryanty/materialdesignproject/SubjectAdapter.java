package com.example.bryanty.materialdesignproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.bryanty.materialdesignproject.network.VolleySingleton;
import com.example.bryanty.materialdesignproject.object.Subject;

import java.util.ArrayList;

/**
 * Created by BRYANTY on 14/05/2015.
 */
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolderSubject> {

    private LayoutInflater layoutInflater;
    private ArrayList<Subject> listSubject= new ArrayList<>();

    //image initial
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public SubjectAdapter(Context context){
        layoutInflater= LayoutInflater.from(context);

        volleySingleton= VolleySingleton.getInstance();
        imageLoader= volleySingleton.getImageLoader();
    }

    public void setSubjectList(ArrayList<Subject> listSubject){
        this.listSubject= listSubject;
        notifyItemRangeChanged(0, listSubject.size());
    }

    @Override
    public ViewHolderSubject onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.custom_row_subject, parent, false);
        ViewHolderSubject viewHolderSubject= new ViewHolderSubject(view);

        return viewHolderSubject;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSubject holder, int position) {

        Subject currentSubject= listSubject.get(position);
        holder.idSubject.setText(currentSubject.getSubjectID());
        holder.titleSubject.setText(currentSubject.getSubjectTitle());

        //special for image load
        String imgURL= "http://76.my/Malaysia/modul-hubungan-etnik-whackthebuzzer-1305-27-whackthebuzzer@6.jpg";

        if(imgURL!= null){
            imageLoader.get(imgURL, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.imageSubject.setImageBitmap(response.getBitmap());

                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return listSubject.size();
    }

    //view holder
    static class ViewHolderSubject extends RecyclerView.ViewHolder{

        private ImageView imageSubject;
        private TextView idSubject;
        private TextView titleSubject;

        public ViewHolderSubject(View itemView){
            super(itemView);
            imageSubject= (ImageView)itemView.findViewById(R.id.imageSubject);
            idSubject= (TextView)itemView.findViewById(R.id.idSubject);
            titleSubject= (TextView)itemView.findViewById(R.id.titleSubject);
        }
    }
}
