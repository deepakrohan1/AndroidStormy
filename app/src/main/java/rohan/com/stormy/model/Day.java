package rohan.com.stormy.model;

/**
 * Created by rohan on 3/10/16.
 */
public class Day {

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

    public double getMaxTemperature() {
        return maxTemperature;
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
