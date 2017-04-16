package intern.tfi.org.prework;

/**
 * Created by basu on 4/16/2017.
 */

public class VolunteeringOpportunities {
    String title,city,area,description,start,end,days,time,email,type;

    public VolunteeringOpportunities(String title, String city, String area, String description, String start, String end, String days, String time, String email, String type) {
        this.title = title;
        this.city = city;
        this.area = area;
        this.description = description;
        this.start = start;
        this.end = end;
        this.days = days;
        this.time = time;
        this.email = email;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
