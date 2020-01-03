package com.example.notebook;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteBookDbAdapter {

    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 1;

    public static final String NOTE_TABLE = "note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";

    private String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_MESSAGE, COLUMN_CATEGORY,
            COLUMN_DATE };

    public static final String CREATE_TABLE_NOTE = "create table " + NOTE_TABLE + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_MESSAGE + " text not null, "
            + COLUMN_CATEGORY + " integer not null, "
            + COLUMN_DATE + ");";

    private SQLiteDatabase sqlDB;
    private Context context;

    private NoteBookDbHelper noteBookDbHelper;

    public NoteBookDbAdapter(Context ctx) {
        context = ctx;
    }

    public NoteBookDbAdapter open() throws  android.database.SQLException {
        noteBookDbHelper = new NoteBookDbHelper(context);
        sqlDB = noteBookDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        noteBookDbHelper.close();
    }

    private static class NoteBookDbHelper extends SQLiteOpenHelper {

        NoteBookDbHelper(Context ctx) {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            // create note table
            db.execSQL(CREATE_TABLE_NOTE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(NoteBookDbHelper.class.getName(),
                     "Upgrading database from version " + oldVersion + " to "
                             + newVersion + ", which will destrol all old data");
            // destroys old data
            db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
            onCreate(db);
        }
    }

}
