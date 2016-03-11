package rohan.com.stormy.model;

import java.util.ArrayList;

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
}
