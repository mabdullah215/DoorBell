package com.mobileapp.doorbell.model;


import java.io.Serializable;

public class EventModel implements Serializable
{
    private int id;
    private String eventName;
    private String eventDescription;
    private int eventDate;
    private int eventMonth;
    private int eventYear;
    private String eventTime;
    private int eventColor;


    public EventModel()
    {
        this.setEventColor(-1);
    }

    public int getEventColor() {
        return eventColor;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventColor(int eventColor) {
        this.eventColor = eventColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getEventDate() {
        return eventDate;
    }

    public void setEventDate(int eventDate) {
        this.eventDate = eventDate;
    }

    public int getEventMonth() {
        return eventMonth;
    }

    public void setEventMonth(int eventMonth) {
        this.eventMonth = eventMonth;
    }

    public int getEventYear() {
        return eventYear;
    }

    public void setEventYear(int eventYear) {
        this.eventYear = eventYear;
    }
}
