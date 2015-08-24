package jamessnee.com.madassignment1_2.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import jamessnee.com.madassignment1_2.R;
import jamessnee.com.madassignment1_2.model.AppData;
import jamessnee.com.madassignment1_2.model.Movie;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populate movie data in the list view
        populateListView();

    }

    private void populateListView(){

        ArrayAdapter<Movie> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);

    }


    //inner class for MyListAdapter
    private class MyListAdapter extends ArrayAdapter<Movie> {

        //get movies from singleton
        public MyListAdapter() {
            super(MainActivity.this, R.layout.list_item, AppData.getInstance().getMovies());
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            //make sure we have a view, if not create one from XML
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }

            //create onclick listener for list item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Movie clickedMovie = AppData.getInstance().getMovie(position);

                    //put intent here to go to next activity
                    Intent detailIntent = new Intent(MainActivity.this, DetailViewActivity.class);
                    //extra to pass in movie variables
                    detailIntent.putExtra("movieTitle", clickedMovie.getTitle());
                    detailIntent.putExtra("movieDesc", clickedMovie.getFull_plot());
                    detailIntent.putExtra("movieShortPlot", clickedMovie.getShort_plot());
                    detailIntent.putExtra("movieYear", clickedMovie.getYear());
                    detailIntent.putExtra("moviePoster", clickedMovie.getPoster());
                    detailIntent.putExtra("movieRating", clickedMovie.getRating());
                    detailIntent.putExtra("movieID", clickedMovie.getId());
                    startActivity(detailIntent);

                }
            });

            //find movie to work with
            final Movie currentMovie = AppData.getInstance().getMovie(position);

            //fill the view
            //poster
            ImageView poster = (ImageView)itemView.findViewById(R.id.poster);
            poster.setImageResource(currentMovie.getPoster());

            //title
            TextView title = (TextView)itemView.findViewById(R.id.item_title);
            title.setText(currentMovie.getTitle());
            //short_plot
            TextView short_plot = (TextView)itemView.findViewById(R.id.item_short_plot);
            short_plot.setText(currentMovie.getShort_plot());
            //year
            TextView year = (TextView)itemView.findViewById(R.id.item_year);
            year.setText(String.valueOf(currentMovie.getYear()));

            //fill rating
            TextView rating = (TextView)itemView.findViewById(R.id.ratingText);
            rating.setText("Rating: " + currentMovie.getRating() + "/5 Stars");

            //RatingBar
            RatingBar ratingBar = (RatingBar)itemView.findViewById(R.id.listRatingBar);
            ratingBar.setRating((int)currentMovie.getRating());

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    ratingBar.setRating((int) rating);
                    currentMovie.setRating((int) rating);

                    //add to model
                    AppData.getInstance().setMovie(currentMovie.getId(), currentMovie);


                }
            });


            return itemView;

        }
    }


}
