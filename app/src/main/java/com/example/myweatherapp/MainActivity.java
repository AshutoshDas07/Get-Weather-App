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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static androidx.core.graphics.drawable.DrawableCompat.*;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable homeicon= AppCompatResources.getDrawable(this,R.drawable.ic_home_black_24dp);
        Drawable wrappedDrawable = wrap(homeicon);
        setTint(wrappedDrawable,Color.RED);
        navigationView =findViewById(R.id.nav_bar);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new HomeFragment()).commit();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment=null;
                switch(item.getItemId()){
                    case R.id.Home:
                        selectedFragment=new HomeFragment();
                        break;
                    case R.id.Forecast:
                        selectedFragment=new ForecastFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectedFragment).commit();
                return true;
            }
        });
    }
}