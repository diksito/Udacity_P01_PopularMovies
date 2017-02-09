package eu.nikolay_angelov.popularmovies.datafrominternet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import eu.nikolay_angelov.popularmovies.movie.MovieContent;

/**
 * Created by Niki on 2/9/2017.
 */

public class JsonParser {

    private static final String TAG_RESULTS = "results";
    private static final String TAG_POSTER_PATH = "poster_path";
    private static final String TAG_ADULT = "adult";
    private static final String TAG_OVERVIEW = "overview";
    private static final String TAG_RELEASE_DATE = "release_date";
    private static final String TAG_ID = "id";
    private static final String TAG_ORIGINAL_TITLE = "original_title";
    private static final String TAG_ORIGINAL_LANGUAGE = "original_language";
    private static final String TAG_TITLE = "title";
    private static final String TAG_BACKDROP_PATH = "backdrop_path";
    private static final String TAG_POPULARITY = "popularity";
    private static final String TAG_VOTE_COUNT = "vote_count";
    private static final String TAG_VIDEO = "video";
    private static final String TAG_VOTE_AVERAGE = "vote_average";

    private String json = null;

    public JsonParser(String json) {
        this.json = json;
    }

    public MovieContent Parse() throws JSONException {

        JSONObject jsonObj = new JSONObject(this.json);

        JSONArray movies = jsonObj.getJSONArray(TAG_RESULTS);

        MovieContent content = new MovieContent(movies.length());

        for (int i = 0; i < movies.length(); i++) {
            JSONObject currJsonMovie = movies.getJSONObject(i);

            MovieContent.MovieItem movie = new MovieContent.MovieItem(currJsonMovie.getString(TAG_ID),
                    currJsonMovie.getString(TAG_OVERVIEW),
                    "",
                    currJsonMovie.getString(TAG_POSTER_PATH),
                    currJsonMovie.getBoolean(TAG_ADULT),
                    currJsonMovie.getDouble(TAG_VOTE_AVERAGE),
                    currJsonMovie.getBoolean(TAG_VIDEO),
                    currJsonMovie.getDouble(TAG_POPULARITY),
                    currJsonMovie.getString(TAG_TITLE),
                    currJsonMovie.getString(TAG_ORIGINAL_TITLE),
                    currJsonMovie.getString(TAG_ORIGINAL_LANGUAGE),
                    currJsonMovie.getString(TAG_RELEASE_DATE));

            content.addItem(movie);

        }
        return content;
    }
}