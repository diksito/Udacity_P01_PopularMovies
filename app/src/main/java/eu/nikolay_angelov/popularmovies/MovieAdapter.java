package eu.nikolay_angelov.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import eu.nikolay_angelov.popularmovies.movie.MovieContent;

/**
 * Created by Niki on 2/8/2017.
 */

public class MovieAdapter extends BaseAdapter {

    private static final String TAG = "MovieAdapter";
    private Context context;
    private int adapterSize = 0;
    private MovieContent content = null;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public MovieAdapter(Context context, MovieContent content) {
        this.adapterSize = content.ITEMS.size();
        this.content = content;
        this.context = context;
    }


    @Override
    public int getCount() {
        return adapterSize;
    }

    @Override
    public Object getItem(int i) {
        return content.ITEMS.get(i);
        //return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (view == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.movie_grid_item, null);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            Log.i(TAG, content.ITEMS.get(i).thumbnailUri);

            Picasso.with(context).load(content.ITEMS.get(i).thumbnailUri).into(imageView);

        } else {
            gridView = (View) view;
        }

        return gridView;
    }
}
