package eu.nikolay_angelov.popularmovies.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MovieContent {

    /**
     * An array of sample (movie) items.
     */
    public static final List<MovieItem> ITEMS = new ArrayList<MovieItem>();

    /**
     * A map of sample (movie) items, by ID.
     */
    public static final Map<String, MovieItem> ITEM_MAP = new HashMap<String, MovieItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(MovieItem item) {
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
    public static class MovieItem {
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
        public  final String originalLanguage;


        public MovieItem(String id, String content, String details, String thumbnailUri, Boolean adult, Double voteAverage, Boolean videoAvailable, Double popularity, String title,  String originalTitle, String originalLanguage) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.thumbnailUri = thumbnailUri;
            this.adult = adult;
            this.voteAverage = voteAverage;
            this.videoAvailable = videoAvailable;
            this.popularity = popularity;
            this.title = title;
            this.originalTitle = originalTitle;
            this.originalTitle = originalLanguage;
        }

        public MovieItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        public MovieItem(String id, String content, String details, String thumbnailUri) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.thumbnailUri = thumbnailUri;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
