package com.example.application;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kerry on 16/05/16.
 */
public class Adapter extends RecyclerView.Adapter<ItemViewHolder> {

    Note[] notes;
    ItemViewHolder.HandleEvent mActivity;

    public Adapter(Note[] notes, ItemViewHolder.HandleEvent activity){
        this.notes = notes;
        this.mActivity = activity;
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

        ItemViewHolder vh = new ItemViewHolder(view, mActivity);

        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(notes[position]);
    }

    @Override
    public int getItemCount() {
        if(notes == null || notes.length == 0)
            return 0;
        else
            return notes.length;
    }
}