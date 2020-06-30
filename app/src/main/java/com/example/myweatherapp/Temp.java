package com.example.myweatherapp;

public class Temp {
    private String min;

    private String max;

    private String eve;

    private String night;

    private String day;

    private String morn;

    public String getMin ()
    {
        return min;
    }

    public void setMin (String min)
    {
        this.min = min;
    }

    public String getMax ()
    {
        return max;
    }

    public void setMax (String max)
    {
        this.max = max;
    }

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
        return "ClassPojo [min = "+min+", max = "+max+", eve = "+eve+", night = "+night+", day = "+day+", morn = "+morn+"]";
    }
}
