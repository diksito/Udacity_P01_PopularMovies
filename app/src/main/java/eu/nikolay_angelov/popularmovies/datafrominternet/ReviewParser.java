package eu.nikolay_angelov.popularmovies.datafrominternet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import eu.nikolay_angelov.popularmovies.movie.ReviewContent;
import eu.nikolay_angelov.popularmovies.movie.ReviewItem;

/**
 * Created by niki on 28/03/2017.
 */

public class ReviewParser {
    public static final String TAG = "ReviewParser";

    private static final String TAG_RESULTS = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_URL = "url";

    private String json = null;
    private ReviewContent content = null;

    public ReviewParser(String json) {
        this.json = json;
        content = new ReviewContent();
    }

    public ReviewContent Parse() throws JSONException {

        JSONObject jsonObj = new JSONObject(this.json);
        JSONArray reviews = jsonObj.getJSONArray(TAG_RESULTS);

        for (int i = 0; i < reviews.length(); i++) {
            JSONObject currJsonReview = reviews.getJSONObject(i);

            ReviewItem item = new ReviewItem(
                    currJsonReview.getString(TAG_ID),
                    currJsonReview.getString(TAG_CONTENT),
                    currJsonReview.getString(TAG_AUTHOR),
                    currJsonReview.getString(TAG_URL));
            this.content.add(item);
        }

        return content;
    }
}