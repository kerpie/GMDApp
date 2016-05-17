package com.example.application.db;

import android.provider.BaseColumns;

/**
 * Created by kerry on 13/05/16.
 */
public class Contract {

    public Contract(){}

    public static abstract class Notes implements BaseColumns {

        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";

    }
}