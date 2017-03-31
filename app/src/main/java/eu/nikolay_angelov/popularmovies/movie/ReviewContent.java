package eu.nikolay_angelov.popularmovies.movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niki on 28/03/2017.
 */

public  class ReviewContent{
    public static final String TAG = "ReviewContent";
    private List<ReviewItem> reviewItemList = null;

    public ReviewContent() {
        this.reviewItemList = new ArrayList<ReviewItem>();
    }
    public void add(ReviewItem item) {
        this.reviewItemList.add(item);
    }
    public  void clear() {
        this.reviewItemList.clear();
    }

    public ReviewItem getItem(int position) {
        return this.reviewItemList.get(position);
    }

    public int getSize() {
        return this.reviewItemList.size();
    }
}
