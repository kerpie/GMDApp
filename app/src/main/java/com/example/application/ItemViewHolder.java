package com.example.application;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kerry on 16/05/16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView mTitle;
    HandleEvent activity;
    Note note;

    public ItemViewHolder(View itemView, final HandleEvent activity) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.title_in_item);

        this.activity = activity;

        itemView.setClickable(true);
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                activity.itemPressed(note.getId());
            }
        });
    }

    public void bind(Note note){
        this.note = note;
        if (note.getTitle() != null || !note.getTitle().isEmpty()){
            mTitle.setText(note.getTitle());
        }
        else{
            mTitle.setText("Texto en blanco");
        }
    }

    public interface HandleEvent{
        public void itemPressed(long id);
    }
}
