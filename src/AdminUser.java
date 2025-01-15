import java.util.HashMap;

public class AdminUser extends User {

    public static HashMap<String, RegularUser> userList = new HashMap<>();

    public AdminUser(){
        super(UserType.ADMIN);
    }


    public AdminUser(String userName, String password, String mailAdress) {
        super(userName, password, mailAdress, UserType.ADMIN);
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    public void deleteUser() {
    }

    //public void listUsers() {}
    //public void updateFilmList() {}
    //public void addFilmToList() {}

    public void updateFilmList(){

    }

    public void deleteFilmFromList() {

    }

    public  void returnToMainMenu(){

        Action.mainMenu();

    }

    public HashMap<String, RegularUser> getUserList() {
        return userList;
    }

    public void addUserToHashMap(RegularUser user) {

        userList.putIfAbsent(user.getUserName(), user);
    }


    @Override
    public String toString() {
        return
                " userType=" + getUserType() +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", mailAdress='" + getMailAdress() ;
    }



}
