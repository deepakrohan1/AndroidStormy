package rohan.com.stormy.model;

import java.util.ArrayList;

import rohan.com.stormy.R;

/**
 * Created by rohan on 3/10/16.
 */
public class Forecast {

    private Current currentWeather;
    private ArrayList<Hour> hoursList;
    private  ArrayList<Day> daysList;

    public Current getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Current currentWeather) {
        this.currentWeather = currentWeather;
    }

    public ArrayList<Hour> getHoursList() {
        return hoursList;
    }

    public void setHoursList(ArrayList<Hour> hoursList) {
        this.hoursList = hoursList;
    }

    public ArrayList<Day> getDaysList() {
        return daysList;
    }

    public void setDaysList(ArrayList<Day> daysList) {
        this.daysList = daysList;
    }

    public static int getIconId(String icon){
        int iconId = R.mipmap.clear_day;
        if (icon.equals("clear-day")) {
            iconId = R.mipmap.clear_day;
        }
        else if (icon.equals("clear-night")) {
            iconId = R.mipmap.clear_night;
        }
        else if (icon.equals("rain")) {
            iconId = R.mipmap.rain;
        }
        else if (icon.equals("snow")) {
            iconId = R.mipmap.snow;
        }
        else if (icon.equals("sleet")) {
            iconId = R.mipmap.sleet;
        }
        else if (icon.equals("wind")) {
            iconId = R.mipmap.wind;
        }
        else if (icon.equals("fog")) {
            iconId = R.mipmap.fog;
        }
        else if (icon.equals("cloudy")) {
            iconId = R.mipmap.cloudy;
        }
        else if (icon.equals("partly-cloudy-day")) {
            iconId = R.mipmap.partly_cloudy;
        }
        else if (icon.equals("partly-cloudy-night")) {
            iconId = R.mipmap.cloudy_night;
        }


        return iconId;
    }
}
