import java.util.HashMap;

public abstract class User {

    private String userName;
    private String password;
    private String mailAdress;

    UserType userType;

    //admin constructor
    public User(String userName, String password, String mailAdress, UserType userType) {

        this.userName = userName;
        this.password = password;
        this.mailAdress = mailAdress;
        this.userType = userType;
    }

    public User() {

    }
    abstract void logout();

    abstract void login();

    abstract void loggedInMenu();


    void returnToMainMenu() {
        Action.mainMenu();
    }

    //kullanıcı ismi listede mevcut ise password kontrolü yapar
    boolean passwordControl() {

        int counter = 0;
        do {
            // döngü bitene kadar password kullanıcıdan input olarak alınır
            System.out.print("---Pasword: ");
            String password = TryCatch.stringInput();
            //eğerki password ile input eşleşiyorsa method sonlanır, true döner
            if (getPassword().equals(password)) {
                return true;
            }

            else System.out.print("Hatalı password.Tekrar deneyin : ");
            counter++;
        } while (counter < 3);  //3 kez şifre girene kadar döngü devam eder

        //password ile input değer eşleşmiyorsa method false döner
        return false;
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

    public UserType getUserType() {
        return userType;
    }

    void register() {
    }
}
