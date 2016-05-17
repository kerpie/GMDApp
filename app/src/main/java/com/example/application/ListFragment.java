package com.example.application;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.application.db.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    RecyclerView mRecyclerView;
    FloatingActionButton mFloatingActionButton;

    SQLiteDatabase mDb;
    DatabaseHelper mDatabaseHelper;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.notes);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.new_note_button);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewNoteActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        for (int i = 0; i < 3; i++) {
            SimpleTask task = new SimpleTask();
            AsyncTaskCompat.executeParallel(task);
        }
    }

    public class SimpleTask extends AsyncTask<Void, Integer, Void>{

        public final String TAG = SimpleTask.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            Toast.makeText(getActivity(), "Hola! El hilo está por empezar", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Un hilo esta por comenzar");
        }

        @Override
        protected Void doInBackground(Void... params) {

            for (int i = 0; i < 6; i++) {
                Double time = Math.random() * 10000;
                try {
                    //jugar con diferentes valores para variar el mensaje
                    int new_time = time.intValue();
                    Thread.sleep(new_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(0, time.intValue());
                publishProgress(1, i);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //Toast.makeText(getActivity(), "Hola! Soy el mensaje numero " + values[0], Toast.LENGTH_SHORT).show();
            if(values[0] == 0)
                Log.i(TAG, "El tiempo de un hilo es " + values[0]);
            else
                Log.i(TAG, "Soy el mensaje numero " + values[1]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(getActivity(), "Hola, ya se ejecutó el hilo", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Un hilo ha finalizado");
        }
    }

}

















