package intern.tfi.org.prework;

/**
 * Created by basu on 4/16/2017.
 */

public class StaffSignUpInfo {
    String name,email,mobile,password,type;

    public StaffSignUpInfo(String name, String email, String mobile, String password,String type) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getType(){
        return type;
    }
}
