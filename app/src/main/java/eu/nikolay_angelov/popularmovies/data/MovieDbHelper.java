package eu.nikolay_angelov.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by niki on 23/03/2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "MovieDbHelper";
    // The name of the database
    private static final String DATABASE_NAME = "moviesDb.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 1;

    // Constructor
    MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tasks table (careful to follow SQL formatting rules)
        final String CREATE_TABLE = "CREATE TABLE "  + MovieContact.MovieEntry.TABLE_NAME + " (" +
                MovieContact.MovieEntry._ID                + " INTEGER PRIMARY KEY, " +
                MovieContact.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_POPULARITY    + " TEXT NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_IMDB_ID + " TEXT NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_VOTE_COUNT + " TEXT NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_ADULT + " INTEGER NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_CONTENT + " TEXT NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_RELEASED_DATE + " TEXT NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_VIDEO_AVAILABLE + " INTEGER NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_ORIGINAL_LANG + " TEXT NOT NULL, " +
                MovieContact.MovieEntry.COLUMN_THUMB_PATH + " TEXT NOT NULL );";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieContact.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}