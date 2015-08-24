package jamessnee.com.madassignment1_2.model;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by jamessnee on 4/08/15. Stores info about Movies
 */
public class Movie {

    private String title;
    private int year;
    private String short_plot;
    private String full_plot;
    private int poster;
    private String id;
    private int rating;


    public Movie(String title, int year, String short_plot, String full_plot, int poster, String id, int rating) {

        super();

        this.title = title;
        this.year = year;
        this.short_plot = short_plot;
        this.full_plot = full_plot;
        this.poster = poster;
        this.id = id;
        this.rating = rating;

    }

    public String getTitle(){
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getShort_plot() {
        return short_plot;
    }

    public String getFull_plot() {
        return full_plot;
    }

    public int getPoster(){
        return poster;
    }

    public String getId() {
        return id;
    }

    public int getRating() { return rating; }

    //setter for rating
    public void setRating(int rating) {
        this.rating = rating;
    }
}
