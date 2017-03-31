package eu.nikolay_angelov.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import eu.nikolay_angelov.popularmovies.movie.ReviewContent;
import eu.nikolay_angelov.popularmovies.movie.ReviewItem;

/**
 * Created by niki on 27/03/2017.
 */

public class ReviewAdapter extends BaseAdapter {
    private static final String TAG = "ReviewAdapter";
    private Context context;
    private ReviewContent content = null;

    public ReviewAdapter(Context context, ReviewContent content) {
        Log.i(TAG, "constructor");
        this.context = context;
        this.content = content;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.content.getSize();
    }

    @Override
    public ReviewItem getItem(int position) {
        return this.content.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Log.i(TAG, "getView position #" + position);

        final ReviewItem item =  this.content.getItem(position);
        if(item == null) {
            Log.i(TAG, "null");
        }
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.review_item, null);
        } else {
            gridView = (View) view;
        }

        TextView reviewText = (TextView)gridView.findViewById(R.id.textViewReviewTitle);
        reviewText.setText(item.getAuthor());

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent youTubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                context.startActivity(youTubeIntent);
            }
        });

        Log.i(TAG, this.content.getItem(position).getAuthor());

        return gridView;
    }
}
