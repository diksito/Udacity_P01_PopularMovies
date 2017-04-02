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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import eu.nikolay_angelov.popularmovies.movie.TrailerContent;
import eu.nikolay_angelov.popularmovies.movie.TrailerItem;

/**
 * Created by niki on 27/03/2017.
 */

public class TrailerAdapter extends BaseAdapter {
    private static final String TAG = "TrailerAdapter";
    private Context context;
    private TrailerContent content = null;

    public TrailerAdapter(Context context, TrailerContent content) {
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
    public TrailerItem getItem(int position) {
        return this.content.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Log.i(TAG, "getView");
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        final TrailerItem item = this.content.getItem(position);

        if (view == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.trailer_item, null);
        } else {
            gridView = (View) view;
        }

        TextView trailerText = (TextView)gridView.findViewById(R.id.textViewTrailerTitle);
        trailerText.setText(item.getName());

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent youTubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+item.getKey()));
                youTubeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(youTubeIntent);
            }
        });

        Log.i(TAG, "Adapter set name: " + this.content.getItem(position).getName());

        return gridView;
    }
}
