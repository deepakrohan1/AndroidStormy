package rohan.com.stormy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rohan on 3/10/16.
 */
public class CurrentWeather {
    private String icon;
    private long time;
    private double temperature;
    private double humidity;
    private double precipChance;
    private String summary;
    private String timeZone;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIconId(){
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

    public long getTime() {
        return time;
    }

    public String getFormattedTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date date = new Date(getTime() * 1000);
        String timeString = dateFormat.format(date);

        return timeString;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTemperature() {
        return (int)Math.round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipChance() {
        return Math.round(precipChance * 100);
    }

    public void setPrecipChance(double precipChance) {
        this.precipChance = precipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
