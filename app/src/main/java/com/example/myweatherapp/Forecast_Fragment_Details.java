package com.example.myweatherapp;

public class Forecast_Fragment_Details {

    private String timezone;

    private String timezone_offset;

    private Daily[] daily;

    private String lon;

    private String lat;

    public String getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (String timezone)
    {
        this.timezone = timezone;
    }

    public String getTimezone_offset ()
    {
        return timezone_offset;
    }

    public void setTimezone_offset (String timezone_offset)
    {
        this.timezone_offset = timezone_offset;
    }

    public Daily[] getDaily ()
    {
        return daily;
    }

    public void setDaily (Daily[] daily)
    {
        this.daily = daily;
    }

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timezone = "+timezone+", timezone_offset = "+timezone_offset+", daily = "+daily+", lon = "+lon+", lat = "+lat+"]";
    }
}
