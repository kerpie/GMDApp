package com.example.application;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.application.db.Contract;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabaseHelper = new DatabaseHelper(getActivity());
        mDb = mDatabaseHelper.getReadableDatabase();
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
        new DBExtractor().execute();
    }

    public class DBExtractor extends AsyncTask<Void, Integer, Void>{

        Cursor cursor;

        @Override
        protected Void doInBackground(Void... params) {
            String[] projection = new String[]{
                    Contract.Notes.COLUMN_TITLE
            };
            String sortOrder = Contract.Notes.COLUMN_TITLE + " desc";
            cursor = mDb.query(
                    Contract.Notes.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String[] values = new String[cursor.getCount()];

            cursor.moveToFirst();
            int i = 0;
            do{
                values[i] = cursor.getString(cursor.getColumnIndex(Contract.Notes.COLUMN_TITLE));
                i++;
            }while (cursor.moveToNext());

            Adapter adapter = new Adapter(values);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mRecyclerView.setAdapter(adapter);
        }
    }

}