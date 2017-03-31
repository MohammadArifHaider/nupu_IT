package com.example.arifhaider.nupuit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by absak on 3/31/2017.
 */

public class recHandler extends RecyclerView.Adapter<recHandler.MyViewHolder> {
    private LayoutInflater inflater;
    List<Contacts> data = Collections.emptyList();
    public recHandler(Context applicationContext, List<Contacts> data){
        inflater = LayoutInflater.from(applicationContext);
        this.data = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contacts c = data.get(position);
        holder.recT1.setText(c.getName());
        holder.recT2.setText(c.getNumber());
        holder.recImg.setImageResource(R.drawable.contact);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView recT1,recT2;
        private ImageView recImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            recT1 = (TextView) itemView.findViewById(R.id.textView2);
            recT2 = (TextView) itemView.findViewById(R.id.textView3);
            recImg = (ImageView) itemView.findViewById(R.id.imageView);
            ;
        }
    }
}
