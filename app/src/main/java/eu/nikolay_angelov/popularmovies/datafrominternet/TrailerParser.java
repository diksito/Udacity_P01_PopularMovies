package eu.nikolay_angelov.popularmovies.datafrominternet;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import eu.nikolay_angelov.popularmovies.movie.TrailerContent;
import eu.nikolay_angelov.popularmovies.movie.TrailerItem;

/**
 * Created by niki on 28/03/2017.
 */

public class TrailerParser {

    public static final String TAG = "TrailerParser";

    private static final String TAG_RESULTS = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_ISO_639_1 = "iso_639_1";
    private static final String TAG_ISO_3166_1 = "iso_3166_1";
    private static final String TAG_KEY = "key";
    private static final String TAG_NAME = "name";
    private static final String TAG_SITE = "site";
    private static final String TAG_SIZE = "size";
    private static final String TAG_TYPE = "type";

    private String json = null;
    private TrailerContent content = null;

    public TrailerParser(String json) {
        this.json = json;
        this.content = new TrailerContent();
    }

    public TrailerContent Parse() throws JSONException {

        JSONObject jsonObj = new JSONObject(this.json);
        JSONArray trailers = jsonObj.getJSONArray(TAG_RESULTS);

        Log.i(TAG, trailers.length() + "");

        for (int i = 0; i < trailers.length(); i++) {
            JSONObject currJsonTrailer = trailers.getJSONObject(i);

            if(!currJsonTrailer.isNull(TAG_KEY)) {
                TrailerItem item = new TrailerItem(
                        currJsonTrailer.getString(TAG_ID),
                        currJsonTrailer.getString(TAG_KEY),
                        currJsonTrailer.getString(TAG_NAME),
                        currJsonTrailer.getString(TAG_SITE),
                        currJsonTrailer.getString(TAG_SIZE),
                        currJsonTrailer.getString(TAG_TYPE),
                        currJsonTrailer.getString(TAG_ISO_639_1),
                        currJsonTrailer.getString(TAG_ISO_3166_1));

                this.content.add(item);
            }
        }
        return this.content;
    }
}
