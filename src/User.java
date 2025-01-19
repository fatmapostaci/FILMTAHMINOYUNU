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
     * This method is called directly from child classes with User referanced objects
     * Terminates the program and prints a termination message to the console.
     * @param exitCode The exit status code for terminating the program. A value of 0 indicates a successful termination
     *
     */
    void terminateTheProgram() {
        System.out.println("Program Sonlandırıldı...");
        System.exit(0);
    }

    boolean passwordControl( String password1) {
        return PasswordFormatControl.getAndValidatePasword2(password1);
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
