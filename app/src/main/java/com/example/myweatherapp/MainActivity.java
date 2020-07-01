package com.example.myweatherapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static androidx.core.graphics.drawable.DrawableCompat.*;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    final Fragment homefragment=new HomeFragment();
    final Fragment searchfragmenet=new ForecastFragment();
    Fragment active=homefragment;
    FragmentManager fragmentManager=getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable homeicon= AppCompatResources.getDrawable(this,R.drawable.ic_home_black_24dp);
        Drawable wrappedDrawable = wrap(homeicon);
        setTint(wrappedDrawable,Color.RED);
        navigationView =findViewById(R.id.nav_bar);
        fragmentManager.beginTransaction().add(R.id.frame_container,searchfragmenet,"2").hide(searchfragmenet).commit();
        fragmentManager.beginTransaction().add(R.id.frame_container,homefragment,"1").commit();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Home:
                        fragmentManager.beginTransaction().hide(active).show(homefragment).commit();
                        active=homefragment;
                        return true;
                    case R.id.Forecast:
                        fragmentManager.beginTransaction().hide(active).show(searchfragmenet).commit();
                        active=searchfragmenet;
                        return true;
                }
                return false;
            }
        });
    }
}