package rohan.com.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rohan on 3/10/16.
 */
public class Hour implements Parcelable {
    private long time;
    private String summary;
    private double temperature;
    private String icon;
    private String timezone;

    public Hour() {

    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTemperature() {
        return (int)Math.round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getIconId(){
        return Forecast.getIconId(getIcon());
    }

    public String getFormattedTime(){
        SimpleDateFormat sf = new SimpleDateFormat("h a");
        sf.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        Date date = new Date(getTime() * 1000);
        return sf.format(date);
    }

    @Override
    public String toString() {
        return "Hour{" +
                "time=" + time +
                ", summary='" + summary + '\'' +
                ", temperature=" + temperature +
                ", icon='" + icon + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(summary);
        dest.writeLong(time);
        dest.writeDouble(temperature);
        dest.writeString(icon);
        dest.writeString(timezone);
    }

    private Hour(Parcel in){
        summary = in.readString();
        time = in.readLong();
        temperature = in.readDouble();
        icon = in.readString();
        timezone = in.readString();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel source) {
            return new Hour(source);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };
}
