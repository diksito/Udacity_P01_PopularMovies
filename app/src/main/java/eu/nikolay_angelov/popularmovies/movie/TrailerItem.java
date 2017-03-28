package eu.nikolay_angelov.popularmovies.movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niki on 28/03/2017.
 */

public class TrailerItem {

    public static final String TAG = "TrailerItem";

    public final String id;
    public final String key;
    public final String name;
    public final String site;
    public final String size;
    public final String type;
    public final String iso_639_1;
    public final String iso_3166_1;

    public TrailerItem(String id, String key, String name, String site, String size, String type, String iso_639_1, String iso_3166_1) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
        this.iso_639_1 = iso_639_1;
        this.iso_3166_1 = iso_3166_1;
    }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public String getType() {
            return type;
        }

        public String getSize() {
            return size;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public String getSite() {
            return site;
        }

        public String getName() {
            return name;
        }

        public String getKey() {
            return key;
        }

        public String getId() {
            return id;
        }
}