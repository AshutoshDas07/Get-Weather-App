package com.example.myweatherapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


    public class HomeFragment extends Fragment {
    TextView place_container,temp_container,weather_status,celsius;
    TextView clouds_text,pressure_text,humidity_text,winds_text;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageView clouds,pressure,humidity,winds,weather_icon;
    String key=BuildConfig.API_Key;
    String BaseUrl = "https://api.openweathermap.org";
    String icon_baseURL="https://openweathermap.org/img/wn/";
    String icon_latterURL="@2x.png";
    DigitalClock clock;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Initialize FusedLocationProviderClient
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        place_container=view.findViewById(R.id.place_container);
        temp_container=view.findViewById(R.id.temp_container);
        weather_status=view.findViewById(R.id.description);
        celsius=view.findViewById(R.id.Celsius);
        weather_icon=view.findViewById(R.id.icon);
        clouds_text=view.findViewById(R.id.clouds_text);
        humidity_text=view.findViewById(R.id.humidity_text);
        pressure_text=view.findViewById(R.id.pressure_text);
        winds_text=view.findViewById(R.id.wind_text);
        clouds=view.findViewById(R.id.clouds);
        humidity=view.findViewById(R.id.humidity);
        pressure=view.findViewById(R.id.pressure);
        winds=view.findViewById(R.id.wind);
        swipeRefreshLayout=view.findViewById(R.id.swiperefresh);
        clock=view.findViewById(R.id.time);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        //check_permission
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //getLocation
            getLocation();
        } else {
            asklocation();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //getLocation
                    getLocation();
                } else {
                    asklocation();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           asklocation();
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {
                if(location!=null) {
                    double latitude=location.getLatitude();
                    double longitude=location.getLongitude();
                    Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();
                    JSONPlaceHolderAPI jsonPlaceHolderAPI=retrofit.create(JSONPlaceHolderAPI.class);
                    Call<Home_Fragment_Details> call=jsonPlaceHolderAPI.getPost(latitude,longitude,"metric",key);
                    Log.e("TAG",call.request().url().toString());
                    call.enqueue(new Callback<Home_Fragment_Details>() {
                        @Override
                        public void onResponse(Call<Home_Fragment_Details> call, Response<Home_Fragment_Details> response) {
                            if(isAdded()&&response.isSuccessful()) {
                                Home_Fragment_Details home_fragment_details = response.body();
                                Spanned superscript = Html.fromHtml("<sup>o</sup>C");
                                double temp = Double.parseDouble(home_fragment_details.getMain().getTemp());
                                temp_container.setText((int) Math.ceil(temp) + "");
                                celsius.setText(superscript);
                                weather_status.setText(home_fragment_details.getWeather()[0].getMain());
                                place_container.setText(home_fragment_details.getName());
                                clouds_text.setText(home_fragment_details.getClouds() + "%");
                                humidity_text.setText(home_fragment_details.getMain().getHumidity() + "%");
                                pressure_text.setText(home_fragment_details.getMain().getPressure());
                                winds_text.setText(home_fragment_details.getWind().getSpeed());
                                Glide.with(getContext()).load(icon_baseURL + home_fragment_details.getWeather()[0].getIcon() + icon_latterURL).into(weather_icon);
                                Log.e("GlideTag", icon_baseURL + home_fragment_details.getWeather()[0].getIcon() + icon_latterURL);
                                setBackground(home_fragment_details.getWeather()[0].getId());
                                clouds.setImageResource(R.drawable.clouds_icon);
                                pressure.setImageResource(R.drawable.airpressure);
                                winds.setImageResource(R.drawable.winds);
                                humidity.setImageResource(R.drawable.humidity);
                                clouds_text.setText(home_fragment_details.getClouds().getAll() + "%");
                                pressure_text.setText(home_fragment_details.getMain().getPressure() + "hPa");
                                humidity_text.setText((home_fragment_details.getMain().getHumidity()) + "%");
                                winds_text.setText(home_fragment_details.getWind().getSpeed() + "m/sec");
                                clock.setVisibility(View.VISIBLE);
                            }else{
                                Toast.makeText(getContext(),"Current Location Not Found",Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Home_Fragment_Details> call, Throwable t) {

                        }
                    });
                }else{
                    Log.e("TAG","Location is Null");
                }
            }
        });
        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG","on Failure"+e.getLocalizedMessage());
            }
        });
    }

    private void setBackground(String id){
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        String time=formatter.format(calendar.getTime());
        int hour=Integer.parseInt(time.substring(0,2));
       if(id.charAt(0)=='2') {
           swipeRefreshLayout.setBackgroundResource(R.drawable.storm);
       }else if(id.charAt(0)=='3'){
           swipeRefreshLayout.setBackgroundResource(R.drawable.lightrain);
       }else if(id.charAt(0)=='5'){
           swipeRefreshLayout.setBackgroundResource(R.drawable.heavyrain);
       }else if(id.charAt(0)=='6') {
            swipeRefreshLayout.setBackgroundResource(R.drawable.cold);
       }else if((id.charAt(0)=='7'||id.equals("800") )&& (hour>=20&&hour<4)){
           swipeRefreshLayout.setBackgroundResource(R.drawable.clearnightsky);
       }else if((id.charAt(0)=='7'||id.equals("800"))&& (hour>16&&hour<20)){
           swipeRefreshLayout.setBackgroundResource(R.drawable.eveningsky);
       }else if((id.charAt(0)=='7'||id.equals("800"))&& (hour>=5&&hour<12)){
           swipeRefreshLayout.setBackgroundResource(R.drawable.sunny);
       }else if((id.charAt(0)=='7'||id.equals("800"))&&(hour>=12&&hour<16)){
           swipeRefreshLayout.setBackgroundResource(R.drawable.hot);
        }else{
           swipeRefreshLayout.setBackgroundResource(R.drawable.cloudy);
       }
    }

    private void asklocation() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(getContext()).setTitle("Permission Required").setMessage("Permission Required for Current Weather Data")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==44){
            if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //Permission Granted
                getLocation();
            }else{
                //Permission Denied
            }
        }
    }

}
