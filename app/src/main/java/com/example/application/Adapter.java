package com.example.application;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kerry on 16/05/16.
 */
public class Adapter extends RecyclerView.Adapter<ItemViewHolder> {

    String[] titles;

    public Adapter(String[] titles){
        this.titles = titles;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.item_recyclerview,
                        parent,
                        false
                );

//        parent.addView(view);

        ItemViewHolder vh = new ItemViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(titles[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}