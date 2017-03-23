package eu.nikolay_angelov.popularmovies.datafrominternet;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Niki on 2/2/2017.
 */

public class NetworkUtils {

    final static String TMDB_BASE_URL =
            "https://api.themoviedb.org/3/movie/"; // "https://api.themoviedb.org/3/movie/76341?api_key={api_key}";

    final static String PARAM_API_KEY = "api_key";
    final static String FOLDER_POPULAR = "popular";
    final static String FOLDER_TOP_RATED = "top_rated";
    final static String PARAM_API_REVIEWS = "reviews";
    final static String PARAM_API_VIDEOS = "videos";
    final static String API_KEY = ""; // // TODO: 2/7/2017  Remove before commiting to GitHub
    private static final String TAG = "NetworkUtils";

    public enum MOVIE_SORT {
        MOST_POPULAR, TOP_RATED, MY_FAVOURTIES
    }

    /**
     * Builds the URL used to query Github.
     *
     * @param sort The keyword that will be queried for.
     * @return The URL to use to query the weather server.
     */

    public static URL buildUrl(MOVIE_SORT sort) {

        Uri builtUri = null;

        switch (sort) {
            case MOST_POPULAR:
                builtUri = Uri.parse(TMDB_BASE_URL + "popular").buildUpon()
                        .appendQueryParameter(PARAM_API_KEY, API_KEY)
                        .build();

                Log.i(TAG, TMDB_BASE_URL + "popular");
                break;
            case TOP_RATED:
                builtUri = Uri.parse(TMDB_BASE_URL + "top_rated").buildUpon()
                        .appendQueryParameter(PARAM_API_KEY, API_KEY)
                        .build();
                Log.i(TAG, TMDB_BASE_URL + "top_rated");
                break;
            case MY_FAVOURTIES:
                // todo: query from here all favourited videos with theid ids
                builtUri = Uri.parse(TMDB_BASE_URL + "top_rated").buildUpon()
                        .appendQueryParameter(PARAM_API_KEY, API_KEY)
                        .build();
                Log.i(TAG, TMDB_BASE_URL + "my favourites");
                break;
        }

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}