package com.example.application;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.db.Contract;
import com.example.application.db.DatabaseHelper;


public class DetailFragment extends Fragment {

    public static final String TAG = DetailFragment.class.getSimpleName();
    public static final String KEY_ID = "key_id";

    long id;

    TextView title;
    TextView description;

    SQLiteDatabase mDb;
    DatabaseHelper mDatabaseHelper;

    public DetailFragment() {
    }

    public static DetailFragment newInstance(long id) {

        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        args.putLong(KEY_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            id = getArguments().getLong(KEY_ID);
        }
        mDatabaseHelper = new DatabaseHelper(getActivity());
        mDb = mDatabaseHelper.getReadableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_detail, container, false);

        title = (TextView) view.findViewById(R.id.detail_title);
        description = (TextView) view.findViewById(R.id.detail_description);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetTaskFromDb().execute();
    }

    public class GetTaskFromDb extends AsyncTask<Void, Integer, Void>{

        Cursor cursor;

        @Override
        protected Void doInBackground(Void... params) {

            String[] projection = new String[]{
                    Contract.Notes._ID,
                    Contract.Notes.COLUMN_TITLE,
                    Contract.Notes.COLUMN_DESCRIPTION
            };

            String selection = "_ID = ?";
            String[] args = new String[]{
                    String.valueOf(id)
            };

            cursor = mDb.query(
                    Contract.Notes.TABLE_NAME,
                    projection,
                    selection,
                    args,
                    null,
                    null,
                    null
            );

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                title.setText(cursor.getString(cursor.getColumnIndex(Contract.Notes.COLUMN_TITLE)));
                description.setText(cursor.getString(cursor.getColumnIndex(Contract.Notes.COLUMN_DESCRIPTION)));
            }
            else{
                Toast.makeText(getActivity(), "No se ha encontrado el valor en la base de datos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}