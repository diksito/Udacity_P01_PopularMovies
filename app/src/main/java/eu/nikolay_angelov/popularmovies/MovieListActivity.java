package eu.nikolay_angelov.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;

import eu.nikolay_angelov.popularmovies.datafrominternet.JsonParser;
import eu.nikolay_angelov.popularmovies.datafrominternet.NetworkUtils;
import eu.nikolay_angelov.popularmovies.movie.MovieContent;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity {

    private static final String TAG = "MovieListActivity";
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private GridView gridView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sortByTopRated) {
            gridView.setAdapter(null);
            makeMovieDbSearchQuery(NetworkUtils.MOVIE_SORT.TOP_RATED);
            return true;

        } else if (id == R.id.action_sortByMostPopular) {
            gridView.setAdapter(null);
            makeMovieDbSearchQuery(NetworkUtils.MOVIE_SORT.MOST_POPULAR);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.movie_grid_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        gridView = (GridView) findViewById(R.id.movies_grid_view);

        makeMovieDbSearchQuery(NetworkUtils.MOVIE_SORT.MOST_POPULAR);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Context context = getApplicationContext();
                MovieContent.MovieItem selectedMovie = (MovieContent.MovieItem)gridView.getAdapter().getItem(position);
                Intent intent = new Intent(context, MovieDetailActivity.class);

                intent.putExtra(MovieDetailFragment.ARG_ITEM_ID, position);
                intent.putExtra(MovieDetailFragment.MOVIE_ID, selectedMovie.id);

                Bundle b = new Bundle();

                b.putParcelable("eu.nikolay_angelov.popularmovies.MovieContent.MovieItem", (Parcelable) selectedMovie);

                intent.putExtras(b);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }
    public class MovieDBQueryTask extends AsyncTask<URL, Void, MovieContent> {

        @Override
        protected MovieContent doInBackground(URL... params) {
            MovieContent content = null;

            URL searchUrl = params[0];
            String jsonResponse = null;
            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(jsonResponse != null) {
                JsonParser parser = new JsonParser(jsonResponse);
                Log.i("JSON PARSER", jsonResponse);
                try {
                    content = parser.Parse();
                    Log.i("JSON PARSER", "passed parsing");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSON PARSER", e.getMessage());
                }
            }

            return content;
        }
        @Override
        protected void onPostExecute(MovieContent githubSearchResults) {
            if (githubSearchResults != null) {
                gridView.setAdapter(new MovieAdapter(getApplicationContext(), githubSearchResults));
            }
        }
    }

    private void makeMovieDbSearchQuery(NetworkUtils.MOVIE_SORT type) {
        URL remoteUrl = NetworkUtils.buildUrl(type);
        new MovieDBQueryTask().execute(remoteUrl);
    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(MovieContent.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<MovieContent.MovieItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<MovieContent.MovieItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(MovieDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        MovieDetailFragment fragment = new MovieDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.movie_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra(MovieDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public MovieContent.MovieItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

}


