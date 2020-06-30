package com.example.myweatherapp;

public class Feels_like {
    private String eve;

    private String night;

    private String day;

    private String morn;

    public String getEve ()
    {
        return eve;
    }

    public void setEve (String eve)
    {
        this.eve = eve;
    }

    public String getNight ()
    {
        return night;
    }

    public void setNight (String night)
    {
        this.night = night;
    }

    public String getDay ()
    {
        return day;
    }

    public void setDay (String day)
    {
        this.day = day;
    }

    public String getMorn ()
    {
        return morn;
    }

    public void setMorn (String morn)
    {
        this.morn = morn;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [eve = "+eve+", night = "+night+", day = "+day+", morn = "+morn+"]";
    }
}
