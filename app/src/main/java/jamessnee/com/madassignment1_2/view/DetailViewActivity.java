package jamessnee.com.madassignment1_2.view;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
    private int position;

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
        position = intent.getIntExtra("position", position);


        //set values to display
        TextView displayText = (TextView)findViewById(R.id.titleDisplay);
        TextView yearView = (TextView)findViewById(R.id.yearView);
        TextView descView = (TextView)findViewById(R.id.movieDescText);
        ImageView displayPoster = (ImageView)findViewById(R.id.posterView);
        RatingBar rating = (RatingBar)findViewById(R.id.detailRatingBar);
        rating.setStepSize(1);
        displayText.setText(titleValue);
        yearView.setText(String.valueOf(yearValue));
        descView.setText(descValue);
        displayPoster.setImageResource(posterValue);
        rating.setRating((int) ratingValue);

        //Get button from UI
        Button schedulePartyButton = (Button)findViewById(R.id.partyButton);
        //Set onClickListener
        schedulePartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createPartyDialog();
            }
        });


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
        AppData.getInstance().getMovie(position).setRating((int) rating);
        Toast.makeText(getApplicationContext(), "The rating was changed to " +
                AppData.getInstance().getMovie(position).getRating(), Toast.LENGTH_SHORT).show();


    }

    //method to create party dialog
    public void createPartyDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false);
        builder.setTitle("Schedule a party");

        //party date picker
        final TextView partyDateTitle = new TextView(this);
        partyDateTitle.setText("Enter party date:");

        DatePicker date = new DatePicker(this);
        date.setCalendarViewShown(false);
        date.setSpinnersShown(true);
        GregorianCalendar cal = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDayOfMonth());


        //party date
        final TextView partyTimeTitle = new TextView(this);
        partyTimeTitle.setText("Enter party time");
        final EditText partyTime = new EditText(this);

        //party venue
        final TextView partyVenueTitle = new TextView(this);
        partyVenueTitle.setText("Enter party venue:");
        final EditText partyVenue = new EditText(this);

        //party location
        final TextView partyLocationTitle = new TextView(this);
        partyLocationTitle.setText("Enter party location:");
        final EditText partyLocation = new EditText(this);

        //add contacts
        final TextView partyInviteesTitle = new TextView(this);
        partyInviteesTitle.setText("Party Invitees:");

        //get all email addresses on device
        ArrayList<String> emails = new ArrayList<String>();

        ContentResolver cr = getContentResolver();
        String id = null, email = null;
        //Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
        //        null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{ id }, null);

        email = "unknown";

        //while(emailCur.moveToNext()){

          //  email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

            if (email == null){
                email = "unknown";
            }

            //add to array
            emails.add(email);

       // }

//        emailCur.close();

        Spinner emailSpinner = new Spinner(this);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emails);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        emailSpinner.setAdapter(spinnerArrayAdapter);


        partyTime.setInputType(InputType.TYPE_CLASS_DATETIME);
        partyVenue.setInputType(InputType.TYPE_CLASS_TEXT);
        partyLocation.setInputType(InputType.TYPE_CLASS_TEXT);


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(partyDateTitle);
        layout.addView(date);
        layout.addView(partyTimeTitle);
        layout.addView(partyTime);
        layout.addView(partyVenueTitle);
        layout.addView(partyVenue);
        layout.addView(partyLocationTitle);
        layout.addView(partyLocation);
        layout.addView(partyInviteesTitle);
        layout.addView(emailSpinner);
        builder.setView(layout);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //save data here
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
