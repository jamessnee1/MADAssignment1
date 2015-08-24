package jamessnee.com.madassignment1_2.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import jamessnee.com.madassignment1_2.R;
import jamessnee.com.madassignment1_2.model.AppData;
import jamessnee.com.madassignment1_2.model.Movie;

public class DetailViewActivity extends ActionBarActivity implements RatingBar.OnRatingBarChangeListener {

    private String movieIDValue;
    private String titleValue;
    private String descValue;
    private String shortPlotValue;
    private int yearValue;
    private int posterValue;
    private int ratingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        //extract extra values from intent for display on the DetailView
        Intent intent = getIntent();
        titleValue = intent.getStringExtra("movieTitle");
        descValue = intent.getStringExtra("movieDesc");
        shortPlotValue = intent.getStringExtra("movieShortPlot");
        yearValue = intent.getIntExtra("movieYear", 0);
        posterValue = intent.getIntExtra("moviePoster", 0);
        ratingValue = intent.getIntExtra("movieRating", 0);
        movieIDValue = intent.getStringExtra("movieID");

        //set values to display
        TextView displayText = (TextView)findViewById(R.id.titleDisplay);
        TextView yearView = (TextView)findViewById(R.id.yearView);
        TextView descView = (TextView)findViewById(R.id.movieDescText);
        ImageView displayPoster = (ImageView)findViewById(R.id.posterView);
        RatingBar rating = (RatingBar)findViewById(R.id.detailRatingBar);
        displayText.setText(titleValue);
        yearView.setText(String.valueOf(yearValue));
        descView.setText(descValue);
        displayPoster.setImageResource(posterValue);
        rating.setRating((int) ratingValue);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        ratingBar.setRating((int) rating);

        //create new movie object from variables
        Movie currentMovie = new Movie(titleValue, yearValue, shortPlotValue, descValue, posterValue, movieIDValue, (int)rating);
        currentMovie.setRating((int) rating);

        //add to model
        AppData.getInstance().setMovie(currentMovie.getId(), currentMovie);


    }

    public void scheduleAPartyButtonPressed(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Schedule a party - " + titleValue);

        //set layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        builder.setView(layout);

        //setup inputs
        final TextView dateAndTimeTitle = new TextView(this);
        dateAndTimeTitle.setText("Enter a Date and Time:");
        layout.addView(dateAndTimeTitle);
        final EditText dateAndTimeInput = new EditText(this);
        dateAndTimeInput.setInputType(InputType.TYPE_CLASS_DATETIME);
        layout.addView(dateAndTimeInput);

        final TextView venueTitle = new TextView(this);
        venueTitle.setText("Enter a venue:");
        layout.addView(venueTitle);
        final EditText venueInput = new EditText(this);
        venueInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(venueInput);

        final TextView locationTitle = new TextView(this);
        locationTitle.setText("Enter a party location (Latitude and Longitude):");
        layout.addView(locationTitle);
        final EditText locationInput = new EditText(this);
        locationInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(locationInput);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //save all data into model, eg
                //String inputText = dateAndTimeInput.getText().toString();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}
