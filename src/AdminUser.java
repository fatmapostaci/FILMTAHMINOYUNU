import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User {

    private List<String> userList;

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

    public void deleteUser(){

    }

    public void listUsers(){

    }
    public void updateFilmList(){

    }
    public void addFilmToList(){

    }
    public void deleteFilmFromList(){

    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }
}
