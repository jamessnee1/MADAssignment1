package jamessnee.com.madassignment1_2.model;

import java.util.GregorianCalendar;

/**
 * Created by jamessnee on 24/08/15.
 */
public class Party {

    private GregorianCalendar partyDate;
    private String partyTime;
    private String partyVenue;
    private String partyLocationLatLng;
    private String[] partyInvitees;


    public Party(GregorianCalendar partyDate, String partyTime, String partyVenue, String partyLocationLatLng, String[] partyInvitees) {
        this.partyDate = partyDate;
        this.partyTime = partyTime;
        this.partyVenue = partyVenue;
        this.partyLocationLatLng = partyLocationLatLng;
        this.partyInvitees = partyInvitees;

    }

    public GregorianCalendar getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(GregorianCalendar partyDate) {
        this.partyDate = partyDate;
    }

    public String getPartyTime() { return partyTime; }

    public void setPartyTime() { this.partyTime = partyTime; }

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

}
