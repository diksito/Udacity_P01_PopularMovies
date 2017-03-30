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

import eu.nikolay_angelov.popularmovies.movie.TrailerContent;

/**
 * Created by niki on 27/03/2017.
 */

public class TrailerAdapter extends BaseAdapter {
    private static final String TAG = "TrailerAdapter";
    private Context context;
    private TrailerContent content = null;

    public TrailerAdapter(Context context, TrailerContent content) {
        this.context = context;
        this.content = content;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (view == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.trailer_item, null);
        } else {
            gridView = (View) view;
        }

        TextView trailerText = (TextView)view.findViewById(R.id.textViewTrailerTitle);
        trailerText.setText(this.content.getItem(position).getName());

        Log.i(TAG, this.content.getItem(position).getName());

        return gridView;
    }
}
