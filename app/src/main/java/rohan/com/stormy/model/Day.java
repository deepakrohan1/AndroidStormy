package rohan.com.stormy.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rohan on 3/10/16.
 */
public class Day implements Serializable{

    private String icon;
    private String summary;
    private String timezone;
    private double maxTemperature;
    private long time;


    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getMaxTemperature() {
        return (int) Math.round(maxTemperature);
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIconId(){
        return Forecast.getIconId(getIcon());
    }

    public String getDayOfWeek(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        Date date = new Date(getTime() * 1000);
        return simpleDateFormat.format(date);

    }

    @Override
    public String toString() {
        return "Day{" +
                "icon='" + icon + '\'' +
                ", summary='" + summary + '\'' +
                ", timezone='" + timezone + '\'' +
                ", maxTemperature=" + maxTemperature +
                ", time=" + time +
                '}';
    }
}
