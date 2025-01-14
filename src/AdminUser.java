import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminUser extends User {

    private HashMap<String, RegularUser> userList;

    public AdminUser(){
        super(UserType.ADMIN);

    }


    public AdminUser(String userName, String password, String mailAdress) {
        super(userName, password, mailAdress, UserType.ADMIN);
    }

    @Override
    public boolean login(String username, String password) {
        return true;
    }

    @Override
    public void logout(String username) {

    }

    public void deleteUser() {

    }

    public void listUsers() {

    }

    public void updateFilmList() {

    }

    public void addFilmToList() {

    }

    public void deleteFilmFromList() {

    }

    public HashMap<String, RegularUser> getUserList() {
        return userList;
    }

    public void setUserList(HashMap<String, RegularUser> userList) {
        this.userList = userList;
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
