package eu.nikolay_angelov.popularmovies.movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niki on 28/03/2017.
 */

public  class TrailerContent{
    public static final String TAG = "TrailerContent";

    private List<TrailerItem> trailerItems = null;

    public TrailerContent() {
        this.trailerItems = new ArrayList<TrailerItem>();
    }
    public void add(TrailerItem item) {
        this.trailerItems.add(item);
    }
    public  void clear() {
        this.trailerItems.clear();
    }

    public TrailerItem getItem(int position) {
        return this.trailerItems.get(position);
    }
}
