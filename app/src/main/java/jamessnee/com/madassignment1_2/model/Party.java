package jamessnee.com.madassignment1_2.model;

/**
 * Created by jamessnee on 24/08/15.
 */
public class Party {

    private String partyDate;
    private String partyVenue;
    private String partyLocationLatLng;
    private String[] partyInvitees;
    private Movie movie;

    public Party(String partyDate, String partyVenue, String partyLocationLatLng, String[] partyInvitees) {
        this.partyDate = partyDate;
        this.partyVenue = partyVenue;
        this.partyLocationLatLng = partyLocationLatLng;
        this.partyInvitees = partyInvitees;
        this.movie = movie;
    }

    public String getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(String partyDate) {
        this.partyDate = partyDate;
    }

    public String getPartyVenue() {
        return partyVenue;
    }

    public void setPartyVenue(String partyVenue) {
        this.partyVenue = partyVenue;
    }

    public String getPartyLocationLatLng() {
        return partyLocationLatLng;
    }

    public void setPartyLocationLatLng(String partyLocationLatLng) {
        this.partyLocationLatLng = partyLocationLatLng;
    }

    public String[] getPartyInvitees() {
        return partyInvitees;
    }

    public void setPartyInvitees(String[] partyInvitees) {
        this.partyInvitees = partyInvitees;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie() {
        this.movie = movie;
    }
}
