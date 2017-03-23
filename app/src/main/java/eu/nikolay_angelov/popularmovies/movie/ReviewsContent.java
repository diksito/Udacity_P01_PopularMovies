package eu.nikolay_angelov.popularmovies.movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */


public class ReviewsContent {

    public ReviewsContent(int size) {
        COUNT = size;
    }

    /**
     * An array of sample (movie) items.
     */
    public static final List<MovieItem> ITEMS = new ArrayList<MovieItem>();

    /**
     * A map of sample (movie) items, by ID.
     */
    public static final Map<String, MovieItem> ITEM_MAP = new HashMap<String, MovieItem>();

    private static int COUNT = 0;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void clear() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }
    public static void addItem(MovieItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static MovieItem createDummyItem(int position) {
        //return new MovieItem(String.valueOf(position), "Item " + position, makeDetails(position));
        return null;
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A movie item representing a piece of content.
     */
    public static class MovieItem implements Parcelable {

        public static final String TAG = "eu.nikolay_angelov.popularmovies.MovieContent.MovieItem";

        public final String id;
        public final String content;
        public final String author;
        public final String url;


        public MovieItem(String id, String content, String author, String url) {
            this.id = id;
            this.content = content;
            this.author = author;
            this.url = url;
        }

        public String getId() {
            return  this.id;
        }
        public MovieItem(Parcel in) {
            this.id = in.readString();
            this.author = in.readString();
            this.content = in.readString();
            this.url = in.readString();

        }
        @Override
        public String toString() {
            return content;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {

            parcel.writeString(this.id);
            parcel.writeString(this.author);
            parcel.writeString(this.content);
            parcel.writeString(this.url);
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public MovieItem createFromParcel(Parcel in) {
                return new MovieItem(in);
            }

            public MovieItem[] newArray(int size) {
                return new MovieItem[size];
            }
        };
    }
}