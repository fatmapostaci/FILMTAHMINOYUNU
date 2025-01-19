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
    void logout() {
        System.out.println("Admin çıkış yaptı");
        System.exit(0);
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

            //password belirlenir
            String password1 = admin.getPassword();
            //User classındaki passwordControl methodu return değerine göre ekrana çıktı yazırır
            boolean loginKontrol = admin.passwordControl(password1);
            if (loginKontrol) {
                System.out.println("Giriş başarılı");
                admin.loggedInMenu();
            } else {
                System.out.println("Login olamadınız. Ana Menüye yönlendiriliyorsunuz!");
                admin.returnToMainMenu();
            }
        }
        //admin listede yok ise sisteme kayıt olur
        else {
            System.out.println("Admin sisteme kayıtlı değil. ^admin^ adıyla ve şifresiyle giriş yapabilirsiniz.!");
            returnToMainMenu();

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
                "3. Admin Yetkili Kullanıcı EKLE \n" +
                "4. Kullanıcı EKLE \n" +
                "5. Kullanıcı SİL \n" +
                "6. Kullanıcı LİSTELE \n" +
                "7. Ana Menüye Dön \n" +
                "8. Çıkış \n" + "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1 -> addFilmToList();
            case 2 -> deleteFilmFromList();
            case 3 -> register();
            case 4 -> addUser();
            case 5 -> deleteUser();
            case 6 -> listUsers();
            case 7 -> returnToMainMenu();
            case 8 -> logout();
            default-> loggedInMenu();   // Repeat the menu for invalid options
        }

    }
    /**
     * Placeholder method for user registration. Currently not implemented.
     */
    @Override
    void register() {
        System.out.println("-------------ADMIN YETKİLİ KULLANICI EKLE-----------------");

        System.out.print("---Admin Name: ");
        String username = TryCatch.stringInput();

        //eğer kullanıcıdan alınan username, userList içinde var ise
        if(AdminUser.getUserList().containsKey(username)){
            System.out.println("Bu kullanıcı sistemde mevcut. Tekrar deneyin");
            register();
        }

        //farklı bir method içinde iki password kullanıcıdan alınıp, eşleştiği kontrolü ve uygun koşulları sağladığı kontrolü yapılacak
        PasswordFormatControl.getAndValidatePassword1and2();
        String acceptedPassword = PasswordFormatControl.getPassword1();

        //MailFormatControl classından static method MailGirisi() çağırılarak, mailin standart koşulları sağladığı sorgulanır
        // mail inputu koşullara uygun olarak kullanıcıdan alınır and assigned to string variable.
        MailFormatControl mfk = new MailFormatControl();
        String mail = mfk.MailGirisi();

        //kullanıcıdan alınan değerler ile newAdminUser objesi üretilir
        User newAdminUser = new AdminUser(username, acceptedPassword, mail);

        //üretilen user userListe eklenir.
        AdminUser.getUserList().putIfAbsent(newAdminUser.getUserName(), newAdminUser);

        if (AdminUser.getUserList().containsKey(username)) {
            System.out.println("Admin Yetkili kullanıcı kaydı başarı ile gerçekleşti. ");

            loggedInMenu();
        }
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
            PasswordFormatControl.getAndValidatePassword1and2();
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

    @Override
    public String toString() {
        return " UserType: "  + getUserType() + "\t\tUsername: " + getUserName() + "\tPassword: " + getPassword() + "\tMail Adress: " + getMailAdress();
    }

    /**
     * Lists all users currently registered in the system.
     */
private void listUsers(){

    System.out.println("----------------Sisteme Kayıtlı Kullanıcı Listesi------------------");
    Set<Map.Entry< String, User >> entrySet = userList.entrySet();

    //AdminUser kullanıcı listesini yazdırır
    for( Map.Entry<String, User> stringAdminEntry  :  entrySet){

        if(stringAdminEntry.getValue().userType == UserType.ADMIN){
            System.out.println(stringAdminEntry.getValue().toString());
        }
    }
    //RegularUser kullanıcı listesini yazdırır
    for (Map.Entry<String, User> stringUserEntry : entrySet) {

        if(stringUserEntry.getValue().userType == UserType.REGULARUSER) {
            System.out.println(stringUserEntry.getValue().toString());
        }
    }

    //Admin menüsüne yönlendirilir
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
        String film = TryCatch.scan.nextLine().toLowerCase().trim();

        boolean silindiMi = false;

        // Remove the film if it exists in the list
        if (Film.filmList.contains(film)) {

            try {
                Film.filmList.remove(film);
                System.out.println("Başarıyla silindi");

            } catch (Exception e) {
                System.out.println("Silinemedi");
            }

        } else {
            System.out.println("Listede bu film mevcut değil!");
        }


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

