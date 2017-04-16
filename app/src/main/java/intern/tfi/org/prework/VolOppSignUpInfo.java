package intern.tfi.org.prework;

/**
 * Created by basu on 4/16/2017.
 */

public class VolOppSignUpInfo {
    String name,email,mobile,reason,experience,commitTime;

    public VolOppSignUpInfo(String name, String email, String mobile, String reason, String experience, String commitTime) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.reason = reason;
        this.experience = experience;
        this.commitTime = commitTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }
}
