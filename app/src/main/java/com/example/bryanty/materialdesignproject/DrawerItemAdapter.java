package com.example.bryanty.materialdesignproject;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by BRYANTY on 29/04/2015.
 */
public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;
    //data
    List<DrawerItem> data= Collections.emptyList();

    public DrawerItemAdapter(Context context, List<DrawerItem> data) {
        this.context= context;
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
    public void onBindViewHolder(final MyViewHolder viewHolder, int i) {
        DrawerItem current= data.get(i);
        viewHolder.icon.setImageResource(current.iconID);
        viewHolder.title.setText(current.title);

        Log.v("myLog","onBindViewHolder called "+i);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //delete list item
    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView icon;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon= (ImageView)itemView.findViewById(R.id.listIcon);
            title= (TextView)itemView.findViewById(R.id.listText);

            //set onClick on icon
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"Item clicked is "+getPosition(),Toast.LENGTH_SHORT).show();

            if(clickListener!= null){
                //pass the view and positon user selected from navigation drawer
                clickListener.itemClicked(v, getPosition());
            }

            //start another activity
            //context.startActivity(new Intent(context, MyActivity.class));

            //delete list item
            //delete(getPosition());
        }
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener= clickListener;
    }

    //interface to navigation drawer item clicked
    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
