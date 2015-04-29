package com.example.bryanty.materialdesignproject;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by BRYANTY on 29/04/2015.
 */
public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    //data
    List<DrawerItem> data= Collections.emptyList();

    public DrawerItemAdapter(Context context, List<DrawerItem> data) {
        inflater= LayoutInflater.from(context);
        this.data= data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.row_item, viewGroup, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        DrawerItem current= data.get(i);
        viewHolder.icon.setImageResource(current.iconID);
        viewHolder.title.setText(current.title);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon= (ImageView)itemView.findViewById(R.id.listIcon);
            title= (TextView)itemView.findViewById(R.id.listText);

        }
    }
}
