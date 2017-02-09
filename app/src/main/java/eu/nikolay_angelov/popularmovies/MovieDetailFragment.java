package eu.nikolay_angelov.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import eu.nikolay_angelov.popularmovies.movie.MovieContent;

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

            Intent intent = this.getActivity().getIntent();

            Bundle b = intent.getExtras();

            MovieContent.MovieItem currentMovie = null;
            if (b != null) {
                currentMovie = b.getParcelable("eu.nikolay_angelov.popularmovies.MovieContent.MovieItem");
                Log.i(TAG, "ARRIVED");
            } else {
                Log.i(TAG, "empty");
            }
/*
            if (b != null) {
                mItem = (MovieContent.MovieItem)b.getParcelable("eu.nikolay_angelov.popularmovies.MovieContent.MovieItem");
                Log.i(TAG, "WORKS!!!");
                Log.i(TAG, currentMovie.title);
            }
*/
            //currentMovie = (MovieContent.MovieItem)intent.getSerializableExtra("eu.nikolay_angelov.popularmovies.MovieContent.MovieItem");

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

            if (appBarLayout != null) {
                appBarLayout.setTitle(currentMovie.title);

            }
        }
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
}
