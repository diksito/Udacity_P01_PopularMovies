package eu.nikolay_angelov.popularmovies;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import eu.nikolay_angelov.popularmovies.data.MovieContact;
import eu.nikolay_angelov.popularmovies.datafrominternet.NetworkUtils;
import eu.nikolay_angelov.popularmovies.datafrominternet.ReviewParser;
import eu.nikolay_angelov.popularmovies.datafrominternet.TrailerParser;
import eu.nikolay_angelov.popularmovies.movie.MovieContent;
import eu.nikolay_angelov.popularmovies.movie.ReviewContent;
import eu.nikolay_angelov.popularmovies.movie.TrailerContent;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String MOVIE_ID = "movie_id";
    private static final String TAG = "MovieDetailFragment";

    /**
     * The movie content this fragment is presenting.
     */
    private MovieContent.MovieItem mItem;
    public static MovieContent.MovieItem currentMovie = null;
    private ReviewAdapter reviewAdapter = null;
    private TrailerAdapter trailerAdapter = null;
    public static ListView trailerListView = null;
    public static ListView reviewListView = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(MOVIE_ID)) {
            Log.i(TAG, getArguments().getString(MOVIE_ID));
            // Load the movie content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            this.trailerListView = (ListView)this.getActivity().findViewById(R.id.movie_trailers_listview);
            this.trailerListView.setDivider(null);
            this.trailerListView.setDividerHeight(0);

            this.reviewListView = (ListView)this.getActivity().findViewById(R.id.review_listview);
            this.reviewListView.setDivider(null);
            this.reviewListView.setDividerHeight(0);

            Intent intent = this.getActivity().getIntent();

            Bundle b = intent.getExtras();


            if (b != null) {
                currentMovie = b.getParcelable(MovieContent.MovieItem.TAG);
            }

            mItem = MovieContent.ITEM_MAP.get(getArguments().getString(MOVIE_ID));
            Log.i(TAG, Integer.toString(MovieContent.ITEMS.size()));

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            ImageView movieThumb = (ImageView) activity.findViewById(R.id.movie_thumbnail);

            Picasso.with(activity.getBaseContext()).load(currentMovie.thumbnailUri).into(movieThumb);

            TextView voteAverage = (TextView)activity.findViewById(R.id.movie_vote);
            voteAverage.setText(currentMovie.voteAverage.toString());

            TextView releasedYear = (TextView)activity.findViewById(R.id.movie_released_date);
            releasedYear.setText(currentMovie.releasedDate.toString());

            EditText overview = (EditText)activity.findViewById(R.id.movie_overview);
            overview.setText(currentMovie.content.toString());
            overview.setEnabled(false);


            // trailers & reviews
            URL remoteUrl = NetworkUtils.buildUrl(NetworkUtils.VIDEO_DATA.REVIEWS, currentMovie.getId());
            new ReviewsQueryTask().execute(remoteUrl);

            URL trailerRemoteUrl = NetworkUtils.buildUrl(NetworkUtils.VIDEO_DATA.TRAILERS, currentMovie.getId());
            new TrailerQueryTask().execute(trailerRemoteUrl);

            if (appBarLayout != null) {
                appBarLayout.setTitle(currentMovie.title);
            }

            Button clickButton = (Button) activity.findViewById(R.id.button_favourite);
            clickButton.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //Log.i(TAG, "clicked...." + currentMovie.title);

                    // Insert new task data via a ContentResolver
                    // Create new empty ContentValues object
                    ContentValues contentValues = new ContentValues();
                    // Put the task description and selected mPriority into the ContentValues
                    contentValues.put(MovieContact.MovieEntry.COLUMN_TITLE, currentMovie.title);
                    contentValues.put(MovieContact.MovieEntry.COLUMN_THUMB_PATH, currentMovie.thumbnailUri);
                    contentValues.put(MovieContact.MovieEntry.COLUMN_IMDB_ID, currentMovie.id);
                    contentValues.put(MovieContact.MovieEntry.COLUMN_VOTE_COUNT, currentMovie.voteAverage);
                    contentValues.put(MovieContact.MovieEntry.COLUMN_POPULARITY, currentMovie.popularity);

                    if(currentMovie.adult)
                        contentValues.put(MovieContact.MovieEntry.COLUMN_ADULT, 1);
                    else
                        contentValues.put(MovieContact.MovieEntry.COLUMN_ADULT, 0);

                    contentValues.put(MovieContact.MovieEntry.COLUMN_ORIGINAL_LANG, currentMovie.originalLanguage);
                    contentValues.put(MovieContact.MovieEntry.COLUMN_ORIGINAL_TITLE, currentMovie.originalTitle);

                    if(currentMovie.videoAvailable)
                        contentValues.put(MovieContact.MovieEntry.COLUMN_VIDEO_AVAILABLE, 1);
                    else
                        contentValues.put(MovieContact.MovieEntry.COLUMN_VIDEO_AVAILABLE, 0);

                    contentValues.put(MovieContact.MovieEntry.COLUMN_CONTENT, currentMovie.content);
                    contentValues.put(MovieContact.MovieEntry.COLUMN_RELEASED_DATE, currentMovie.releasedDate);

                    // Insert the content values via a ContentResolver

                    Uri uri = v.getContext().getContentResolver().insert(MovieContact.MovieEntry.CONTENT_URI, contentValues);

                    // Display the URI that's returned with a Toast
                    // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
                    if(uri != null) {
                        Toast.makeText(v.getContext(), uri.toString(), Toast.LENGTH_LONG).show();
                    }

                    // Finish activity (this returns back to MainActivity)
                    //finish();
                }
            });

            setRetainInstance(true);
        }
    }

    public void setData(MovieContent.MovieItem data) {
        this.mItem = data;
    }

    public MovieContent.MovieItem getData() {
        return mItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        // Show the movie content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.movie_detail)).setText(mItem.details);
        }

        return rootView;
    }

    public class TrailerQueryTask extends AsyncTask<URL, Void, TrailerContent> {

        @Override
        protected TrailerContent doInBackground(URL... params) {
            TrailerContent content = null;

            URL searchUrl = params[0];
            Log.i(TAG, searchUrl.toString());
            String jsonResponse = null;
            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(jsonResponse != null) {
                TrailerParser parser = new TrailerParser(jsonResponse);
                try {
                    content = parser.Parse();
                    Log.i(TAG, "trailer passed parsing");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }
            }
            return content;
        }
        @Override
        protected void onPostExecute(TrailerContent trailerData) {
            if (trailerData != null) {
                if(trailerListView == null)
                    Log.i(TAG, "trailerListView is null");
                trailerListView.setAdapter(new TrailerAdapter(getActivity().getApplicationContext(), trailerData));
            }
        }
    }
    public class ReviewsQueryTask extends AsyncTask<URL, Void, ReviewContent> {

        @Override
        protected ReviewContent doInBackground(URL... params) {
            ReviewContent content = null;

            URL searchUrl = params[0];
            Log.i(TAG, searchUrl.toString());
            String jsonResponse = null;
            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(jsonResponse != null) {
                ReviewParser parser = new ReviewParser(jsonResponse);
                try {
                    content = parser.Parse();
                    Log.i(TAG, "reviews passed parsing");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }
            }
            return content;
        }
        @Override
        protected void onPostExecute(ReviewContent reviewData) {

            if(reviewData != null) {
                reviewListView.setAdapter(new ReviewAdapter(getActivity().getApplicationContext(), reviewData));
            }
            // update adapter
            /*
            if (movieSearchResults != null) {
                gridView.setAdapter(new MovieAdapter(getApplicationContext(), movieSearchResults));
            }
            */
        }
    }
}
