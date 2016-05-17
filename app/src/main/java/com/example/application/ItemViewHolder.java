package com.example.application;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kerry on 16/05/16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView mTitle;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.title_in_item);
    }

    public void bind(String title){
        if (title != null || !title.isEmpty()){
            mTitle.setText(title);
        }
        else{
            mTitle.setText("Texto en blanco");
        }
    }
}
