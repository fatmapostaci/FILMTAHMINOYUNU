import java.util.*;

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
     * calls the super constructor with UserType.ADMIN added
     *                                  UserType is an enum
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
        return " userType=" + getUserType() + ", userName='" + getUserName() + '\'' + ", password='" + getPassword() + '\'' + ", mailAdress='" + getMailAdress();
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
        if (getUserList().containsKey(username) && getUserList().get(username).getUserType() == UserType.ADMIN) {

            //username key değerindeki User admin objesine atanır
            admin = getUserList().get(username);

            //AdminUser classındaki login methodu return değerine göre ekrana çıktı yazırır
            boolean loginKontrol = admin.passwordControl();
            if (loginKontrol) {
                System.out.println("Giriş başarılı");
                admin.loggedInMenu();
            } else {
                System.out.println("Login olamadınız. Yönetici yetkisi ile şifrenize ulaşışıyor!");
                System.out.println("admin.pasword = " + admin.getPassword());
                System.out.println("Tekrar deneyin!");
                admin.passwordControl();
                System.out.println("Giriş başarılı");  //??????
                admin.loggedInMenu();
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
                "5. Kullanıcı LİSTELE \n" +
                "6. Ana Menüye Dön \n" +
                "7. Çıkış \n" + "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1 -> addFilmToList();
            case 2 -> deleteFilmFromList();
            case 3 -> addUser();
            case 4 -> deleteUser();
            case 5 -> listUsers();
            case 6 ->  returnToMainMenu();
            case 7 -> logout();
            default-> loggedInMenu();   // Repeat the menu for invalid options
        }

    }
    /**
     * Placeholder method for user registration. Currently not implemented.
     */
    @Override
    void register() {

    }
    /**
     * Deletes a user from the system based on their username. Ensures admin users cannot be deleted.
     */
    private void deleteUser() {

        System.out.println("-------------SİSTEMDEN USER SİL----------------------");

        System.out.print("---Username: ");
        String username = TryCatch.stringInput();

        //userList içinde username key değeri yak ise
        if (!AdminUser.getUserList().containsKey(username)){
            System.out.println("Sistemde böyle bir kullanıcı yok!");
            return;   // false ise kullanıcı sistemde vardır
        }
        //silinmek istenen kullanıcı admin yetkisine sahip ise silinemez
        else if (AdminUser.getUserList().get(username).getUserType()==UserType.ADMIN) {
            System.out.println("Admin Yetkisi olan kullanıcı silinemez!");
        }
        //regular user olan ve listede var olan user listeden silinir
        else {
            AdminUser.getUserList().remove(username);
            System.out.println("Kullanıcı başarı ile silindi.");
        }

        // İşlem tamamlandıktan sonra menüye dön
        loggedInMenu();
    }
    /**
     * Adds a new regular user to the system after collecting their information.
     */
    private void addUser() {
        System.out.println("-------------SİSTEME USER EKLE----------------------");

        boolean isUserAbsent=true;  // true ise kullanıcı sistemde yoktur

        System.out.print("---Username: ");
        String username = TryCatch.stringInput();

        if (AdminUser.getUserList().containsKey(username)){
            isUserAbsent=false;   // false ise kullanıcı sistemde vardır
        }
        //else case kullanıcı sistemde yok ise çalışır
        else {

            //password belirlenir
            //farklı bir method içinde iki password kontrolü yapılacak
            PasswordFormatControl.getAndValidatePassword();
            String acceptedPassword=  PasswordFormatControl.getPassword1();

            //mail belirlenir
            MailFormatControl mfk = new MailFormatControl();
            String mail = mfk.MailGirisi();
           // System.out.println(mail);

            //kullanıcıdan alınan değerler ile newUser objesi üretilir
            User newUser = new RegularUser(username, acceptedPassword, mail);

            //üretilen user userListe eklenir.
            AdminUser.getUserList().putIfAbsent(newUser.getUserName(), newUser);
        }
        //sistemde kullanıcı yok ise
        if (isUserAbsent) {
            System.out.println("Kullanıcı kaydı başarı ile gerçekleşti. ");
        }
        else System.out.println("Kullanıcı eklenemedi.^" + username +"^ adında kullanıcı sistemde mevcut!");

        // İşlem tamamlandıktan sonra menüye dön
        loggedInMenu();
    }
    /**
     * Lists all users currently registered in the system.
     */
private void listUsers(){
    System.out.println("-------------Sisteme Kayıtlı Kullanıcı Listesi--------------------------");
    Set<Map.Entry< String, User >> entrySet = userList.entrySet();

    for (Map.Entry<String, User> stringUserEntry : entrySet) {
        System.out.println(stringUserEntry.getValue());
    }
    //todo program sonlanıyor düzeltilecek
    loggedInMenu();
}

    /**
     * Deletes a film from the film list based on user input.
     * Ensures that the film exists before attempting to delete it.
     */
    private void deleteFilmFromList() {
        System.out.println("----------------FILM SİL----------------------");

// Print the current film list
        listFilms();


        // Get the name of the film to delete   ///trycatch
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
        } else System.out.println("Silinemedi");

// Print the current film list
        listFilms();
        // İşlem tamamlandıktan sonra menüye dön
        loggedInMenu();

    }

    /**
     * Adds a film to the film list based on user input.
     */
    private void addFilmToList() {
        System.out.println("----------------FILM EKLE----------------------");

        // Print the current film list
        listFilms();


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
        } else System.out.println("Eklenemedi");

        // Print the current film list
        listFilms();

        // İşlem tamamlandıktan sonra menüye dön
        loggedInMenu();
    }

    // Print the current film list
    private void listFilms() {
        System.out.print("Mevcut Film Listesi : ");
        for (String s : Film.filmList) {
            System.out.print(s + ",\t");
        }
        System.out.println();
    }

}

