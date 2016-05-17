package com.example.application;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.application.db.Contract;
import com.example.application.db.DatabaseHelper;

public class NewNoteActivity extends AppCompatActivity {

    EditText mTitle;
    EditText mDescription;
    Button mSave;

    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        mTitle = (EditText) findViewById(R.id.new_title);
        mDescription = (EditText) findViewById(R.id.new_description);
        mSave = (Button) findViewById(R.id.save_note);

        mDatabaseHelper = new DatabaseHelper(this);
        mDatabase = mDatabaseHelper.getWritableDatabase();

        mSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void insert(){
        new DBInsert().execute();
    }

    public class DBInsert extends AsyncTask<Void, Integer, Void>{

        String title;
        String description;
        long id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            title = mTitle.getText().toString().trim();
            description = mDescription.getText().toString().trim();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(Contract.Notes.COLUMN_TITLE, title);
            contentValues.put(Contract.Notes.COLUMN_DESCRIPTION, description);

            id = mDatabase.insert(
                    Contract.Notes.TABLE_NAME,
                    null,
                    contentValues
            );

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(id != -1){
                Toast.makeText(NewNoteActivity.this, "Guardado con exito", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(NewNoteActivity.this, "Guardado fallido", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
