import java.util.HashMap;
/**
 * Abstract class representing a User in the system.
 * This class includes common properties and methods for all user types.
 */
public abstract class User {

    private String userName;
    private String password;
    private String mailAdress;

    UserType userType;

    /**
     * Constructor to initialize a User object with user details.
     * @param userName The username of the user.
     * @param password The password of the user.
     * @param mailAdress The email address of the user.
     * @param userType The type of user (e.g., Admin, Regular).
     */
    public User(String userName, String password, String mailAdress, UserType userType) {

        this.userName = userName;
        this.password = password;
        this.mailAdress = mailAdress;
        this.userType = userType;
    }
    /**
     * Default constructor.
     */
    public User() {

    }
    /**
     * Method to log in a user. This must be implemented by subclasses.
     */
    abstract void login();
    /**
     * Method to log out a user. This must be implemented by subclasses.
     */
    abstract void logout();
    /**
     * Method for the user to access the logged-in menu. This must be implemented by subclasses.
     */
    abstract void loggedInMenu();
    /**
     * Method to register a user. This must be implemented by subclasses.
     */
    abstract void register();
    /**
     * Returns the user to the main menu.
     * This method is called directly from child classes with User referanced objects
     */
    void returnToMainMenu() {
        Action.mainMenu();
    }
    /**
     * Checks the user's password by comparing it with the input.
     * The user has three attempts to enter the correct password.
     * @return true if the password matches, false otherwise.
     */
    boolean passwordControl() {

        //kullanıcı ismi listede mevcut ise password kontrolü yapar
        int counter = 0;
        do {
            // döngü bitene kadar password kullanıcıdan input olarak alınır
            System.out.print("---Pasword: ");
            String password = TryCatch.scan.next();
            //eğerki password ile input eşleşiyorsa method sonlanır, true döner
            if (getPassword().equals(password)) {
                return true;
            }

            else System.out.print("Hatalı password.Tekrar deneyin! \n");
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

}
