package com.example.myweatherapp;

public class Daily {

    private Temp temp;

    private String clouds;

    private Feels_like feels_like;

    private String dt;

    private Weather[] weather;


    public Temp getTemp ()
    {
        return temp;
    }

    public void setTemp (Temp temp)
    {
        this.temp = temp;
    }

    public String getClouds ()
    {
        return clouds;
    }

    public void setClouds (String clouds)
    {
        this.clouds = clouds;
    }

    public Feels_like getFeels_like ()
    {
        return feels_like;
    }

    public void setFeels_like (Feels_like feels_like)
    {
        this.feels_like = feels_like;
    }

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }
    @Override
    public String toString()
    {
        return "ClassPojo [ temp = "+temp+",clouds = "+clouds+", feels_like = "+feels_like+", dt = "+dt+", weather = "+weather+"]";
    }
}
