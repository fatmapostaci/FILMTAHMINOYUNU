import java.util.List;

public abstract class User implements UserActions {


    private String userName;
    private String password;
    private String mailAdress;

    UserType userType;

    public User(UserType userType) {
        this.userType = userType;
    }

    //admin constructor
    public User(String userName, String password, String mailAdress, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.mailAdress = mailAdress;
        this.userType = userType;
    }
    public abstract boolean logout();
    public abstract boolean login();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }


    public UserType getUserType() {
        return userType;
    }


}
