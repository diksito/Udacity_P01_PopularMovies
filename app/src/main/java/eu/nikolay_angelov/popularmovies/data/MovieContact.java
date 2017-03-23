package eu.nikolay_angelov.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by niki on 23/03/2017.
 */

public class MovieContact {
    private static final String TAG = "MovieContact";
    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "eu.nikolay_angelov.popularmovies";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_MOVIES = "movies";


    public static final class MovieEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        // Task table and column names
        public static final String TABLE_NAME = "movies";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMDB_ID = "imdb_id";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_THUMB_PATH = "thumbnail_path";

    }
}
