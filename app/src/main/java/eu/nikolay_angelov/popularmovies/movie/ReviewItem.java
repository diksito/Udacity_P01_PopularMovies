package eu.nikolay_angelov.popularmovies.movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niki on 28/03/2017.
 */

public class ReviewItem {
    public static final String TAG = "ReviewItem";

    public final String id;
    public final String content;
    public final String author;
    public final String url;

    public ReviewItem(String id, String content, String author, String url) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.url = url;
    }

    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getUrl() {
        return this.url;
    }
}