import java.util.HashMap;
import java.util.Objects;

/**
 * Represents an administrative user with special permissions to manage users and films.
 * Extends the {@link User} class with additional functionality.
 */
public class AdminUser extends User {

    /**
     * The currently logged-in admin user.
     */
    static User admin;

    /**
     * Stores the list of all users, with usernames as keys.
     */
    private static HashMap<String, User> userList = new HashMap<>();

    /**
     * Constructs an AdminUser with the specified username, password, and email address.
     *
     * @param userName   the username of the admin user
     * @param password   the password of the admin user
     * @param mailAdress the email address of the admin user
     */
    AdminUser(String userName, String password, String mailAdress) {
        super(userName, password, mailAdress, UserType.ADMIN);
    }

    /**
     * Default constructor for creating an AdminUser.
     */
    public AdminUser() {
        super();
    }

    /**
     * Adds a user to the user list if they are not already present.
     *
     * @param user the user to be added
     */
    public void addUserToHashMap(User user) {
            getUserList().putIfAbsent(user.getUserName(), user);

    }

    static HashMap<String, User> getUserList() {
        return userList;
    }

     static void setUserList(HashMap<String, User> userList) {
        AdminUser.userList = userList;
    }

    @Override
    public String toString() {
        return
                " userType=" + getUserType() +
                        ", userName='" + getUserName() + '\'' +
                        ", password='" + getPassword() + '\'' +
                        ", mailAdress='" + getMailAdress();
    }

    @Override
    void logout() {
        System.out.println("Admin çıkış yaptı");
        System.exit(1);
    }
    /**
     * Logs in the admin user by validating their username and password.
     */
    @Override
    void login() {

        System.out.println("----------------ADMIN-----------------------");

        System.out.print("---Username: ");
        String username = TryCatch.stringInput();

        //admin listede varsa password sorarak login olur.
        if (getUserList().containsKey(username) ) {

            //username key değerindeki User admin objesine atanır
            admin = getUserList().get(username);
            System.out.println(admin);

            //AdminUser classındaki login methodu return değerine göre ekrana çıktı yazırır
            boolean loginKontrol = admin.passwordControl();
            if (loginKontrol) {
                System.out.println("Giriş başarılı");
                loggedInMenu();
            } else {
                System.out.println("Login olamadınız. Yönetici yetkisi ile şifrenize ulaşışıyor!");
                System.out.println("admin.pasword = " + admin.getPassword());
                System.out.println("Tekrar deneyin!");
                admin.passwordControl();
            }

        }
        //admin listede yok ise sisteme kayıt olur
        else {
            System.out.println("Admin sisteme kayıtlı değil.");
            System.exit(0);
            //username parametreli constructor çağırılır
            //admin = new AdminUser(username);
            //yeni admin eklemek için buradan uygun method çağırılarak geliştirme yapılabilir
        }
    }
    /**
     * loggedInMenu() overrides from User Class
     * Displays the admin menu and handles menu actions.
     */
    @Override
     void loggedInMenu() {
        System.out.print("----------------ADMIN MENU-----------------------\n" +
                "1. Film EKLE \n" +
                "2. Film SİL \n" +
                "3. Kullanıcı EKLE \n" +
                "4. Kullanıcı SİL \n" +
                "5. Ana Menüye Dön \n" +
                "6. Çıkış \n" +
                "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1->   addFilmToList();
            case 2->   deleteFilmFromList();
            case 3->   addUser();
            case 4->   deleteUser();
            case 5->   returnToMainMenu();
            case 6->   logout();
            default->  loggedInMenu();   // Repeat the menu for invalid options
        }

    }

    private void deleteUser() {
    }
    private void addUser() {
    }


    /**
     * Deletes a film from the film list based on user input.
     */
    private void deleteFilmFromList() {
        System.out.println("----------------FILM SİL----------------------");

        // Print the current film list
        for (String s : Film.filmList) {
            System.out.print(s + ",\t");
        }
        System.out.println();

        // Get the name of the film to delete
        System.out.print("Silinecek Film Adı : ");
        String film = TryCatch.stringInput();

        boolean silindiMi = false;

        // Remove the film if it exists in the list
        if (Film.filmList.contains(film)) {
            silindiMi = Film.filmList.remove(film);
        } else {
            System.out.println("Listede bu film mevcut değil!");
        }

        if (silindiMi) {
            System.out.println("Başarıyla silindi");
        }

    }

    /**
     * Adds a film to the film list based on user input.
     */
    private void addFilmToList() {
        System.out.println("----------------FILM EKLE----------------------");

        // Print the current film list
        for (String s : Film.filmList) {
            System.out.print(s + ",\t");
        }
        System.out.println();

        // Get the name of the film to add
        System.out.print("Eklenecek Film Adı : ");
        String film = TryCatch.stringInput();

        boolean eklendiMi = false;

        // Add the film if it does not already exist
        if (Film.filmList.contains(film)) {
            System.out.println("Listede bu film zaten mevcut!");
        } else {
            eklendiMi = Film.filmList.add(film);
        }

        if (eklendiMi) {
            System.out.println("Başarıyla eklendi");
        }
    }

//public void listUsers() {}

}

