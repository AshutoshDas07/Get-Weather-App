package com.example.myweatherapp;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.OkHttpClient;

public class Forecast_Adapter extends RecyclerView.Adapter<Forecast_Adapter.ForeCastViewHolder>{
    int selectedPosition=-1;
    Context context;
    ArrayList<Daily> list;
    String icon_baseURL="https://openweathermap.org/img/wn/";
    String icon_latterURL="@2x.png";
    String[] days={"","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};


    public Forecast_Adapter(Context context, ArrayList<Daily> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ForeCastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.all_days_forecast_item_cardview,parent,false);
        return new ForeCastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForeCastViewHolder holder, final int position) {
        Daily daily=list.get(position);
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(daily.getDt())*1000);
        int Day=calendar.get(Calendar.DAY_OF_WEEK);
        Spanned superscript= Html.fromHtml("<sup>o</sup>C");
        double temp=Double.parseDouble(daily.getTemp().getDay());
        holder.temp.setText((int)Math.ceil(temp)+"");
        holder.day.setText(days[Day]);
        Glide.with(context).load(icon_baseURL+daily.getWeather()[0].getIcon()+icon_latterURL).into(holder.icon);
        holder.unit.setText(superscript);
        if(selectedPosition==position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#a8ccd7"));
        }
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ForeCastViewHolder extends RecyclerView.ViewHolder{
        TextView day;
        ImageView icon;
        TextView temp;
        TextView unit;

        public ForeCastViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.daily_icon);
            temp=itemView.findViewById(R.id.daily_temp);
            day=itemView.findViewById(R.id.day);
            unit=itemView.findViewById(R.id.daily_celsius);
        }
    }

}
