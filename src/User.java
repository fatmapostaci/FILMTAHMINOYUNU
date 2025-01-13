public class User {


    private String userName;
    private String password;
    private String mailAdress;
    final int maxLoginAttempt=3;
    private int loginAttempts;
    UserType userType;

    //admin constructor
    public User(String userName, String password, String mailAdress, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.mailAdress = mailAdress;
        this.userType = userType;

    }

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

    public int getMaxLoginAttempt() {
        return maxLoginAttempt;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
