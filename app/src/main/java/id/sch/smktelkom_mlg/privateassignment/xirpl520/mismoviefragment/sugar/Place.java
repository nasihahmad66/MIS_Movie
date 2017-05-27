package id.sch.smktelkom_mlg.privateassignment.xirpl520.mismoviefragment.sugar;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Samudra~ on 5/15/2017.
 */

public class Place extends SugarRecord implements Serializable {
    public String overview;
    public String release_date;
    public String title;
    byte[] backdrop_path = new byte[2048];

    public Place() {

    }

    public Place(String overview, String release_date, String title, byte[] backdrop_path) {
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.backdrop_path = backdrop_path;

    }

}
