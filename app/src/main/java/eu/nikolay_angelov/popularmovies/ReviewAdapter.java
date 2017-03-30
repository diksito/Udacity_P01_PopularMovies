package eu.nikolay_angelov.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import eu.nikolay_angelov.popularmovies.movie.ReviewContent;

/**
 * Created by niki on 27/03/2017.
 */

public class ReviewAdapter extends BaseAdapter {
    private static final String TAG = "ReviewAdapter";
    private Context context;
    private ReviewContent content = null;

    public ReviewAdapter(Context context, ReviewContent content) {
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

        if (view == null) {
            view = new View(context);
            view = inflater.inflate(R.layout.review_item, null);
        } else {
            view = (View) view;
        }

        TextView trailerText = (TextView)view.findViewById(R.id.textViewTrailerTitle);
        trailerText.setText(this.content.getItem(position).getAuthor());

        Log.i(TAG, this.content.getItem(position).getAuthor());

        return view;
    }
}
