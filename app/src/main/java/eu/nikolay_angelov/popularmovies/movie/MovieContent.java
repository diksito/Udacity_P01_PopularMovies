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


public class MovieContent {

    public MovieContent(int size) {
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
        return new MovieItem(String.valueOf(position), "Item " + position, makeDetails(position));
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
        public final String details;
        public final String thumbnailUri;
        public final Boolean adult;
        public final Double voteAverage;
        public final Boolean videoAvailable;
        public final Double popularity;
        public final String title;
        public final String originalTitle;
        public final String originalLanguage;
        public final String releasedDate;


        public MovieItem(String id, String content, String details, String thumbnailUri, Boolean adult, Double voteAverage, Boolean videoAvailable, Double popularity, String title,  String originalTitle, String originalLanguage, String releasedDate) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.thumbnailUri = "http://image.tmdb.org/t/p/w154/" + thumbnailUri;
            this.adult = adult;
            this.voteAverage = voteAverage;
            this.videoAvailable = videoAvailable;
            this.popularity = popularity;
            this.title = title;
            this.originalTitle = originalTitle;
            this.originalLanguage = originalLanguage;
            this.releasedDate = releasedDate;
        }

        public String getId()
        {
            return  this.id;
        }
        public MovieItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.thumbnailUri = "";
            this.adult = false;
            this.voteAverage = 0.0;
            this.videoAvailable = false;
            this.popularity = 0.0;
            this.title = "";
            this.originalTitle = "";
            this.originalLanguage = "";
            this.releasedDate = "";
        }

        public MovieItem(String id, String content, String details, String thumbnailUri) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.thumbnailUri = "http://image.tmdb.org/t/p/w154/" + thumbnailUri;
            this.adult = false;
            this.voteAverage = 0.0;
            this.videoAvailable = false;
            this.popularity = 0.0;
            this.title = "";
            this.originalTitle = "";
            this.originalLanguage = "";
            this.releasedDate = "";
        }

        public MovieItem(Parcel in) {
            this.id = in.readString();
            this.title = in.readString();
            this.content = in.readString();
            this.details = in.readString();
            this.thumbnailUri = in.readString();
            this.voteAverage = in.readDouble();
            this.popularity = in.readDouble();
            this.originalTitle = in.readString();
            this.originalLanguage = in.readString();
            this.releasedDate = in.readString();

            this.adult = in.readByte() != 0;
            this.videoAvailable = in.readByte() != 0;

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
            parcel.writeString(this.title);
            parcel.writeString(this.content);
            parcel.writeString(this.details);
            parcel.writeString(this.thumbnailUri);
            parcel.writeDouble(this.voteAverage);
            parcel.writeDouble(this.popularity);
            parcel.writeString(this.originalTitle);
            parcel.writeString(this.originalLanguage);
            parcel.writeString(this.releasedDate);

            parcel.writeByte((byte) (this.adult ? 1 : 0));
            parcel.writeByte((byte) (this.videoAvailable ? 1 : 0));
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