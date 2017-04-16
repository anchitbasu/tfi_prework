package intern.tfi.org.prework;

/**
 * Created by basu on 4/16/2017.
 */

public class VolunteerOpportunityInfo {
    String title,city,area,emailid,type,start,end,time,days,description,userid;

    public VolunteerOpportunityInfo(String title, String city, String area, String emailid, String type, String start, String end, String time, String days, String description, String userid) {
        this.title = title;
        this.city = city;
        this.area = area;
        this.emailid = emailid;
        this.type = type;
        this.start = start;
        this.end = end;
        this.time = time;
        this.days = days;
        this.description = description;
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getType() {
        return type;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getTime() {
        return time;
    }

    public String getDays() {
        return days;
    }

    public String getDescription() {
        return description;
    }

    public String getUserid() {
        return userid;
    }
}
