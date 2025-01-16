
/**
 * Represents a regular user in the system with specific functionalities.
 * This class extends the User class.
 */
public class RegularUser extends User {


    Film film;

    static User user;
    /**
     * Constructs a RegularUser with the specified username, password, and email address.
     *
     * @param userName   the username of the user
     * @param password   the password of the user
     * @param mailAdress the email address of the user
     */
    RegularUser(String userName, String password, String mailAdress) {
        super(userName, password, mailAdress, UserType.REGULARUSER);
    }
    /**
     * Default constructor for creating a RegularUser instance.
     */
    RegularUser() {
        super();
    }
    /**
     * Starts the game and shows the logged-in menu for the user.
     */
    public void playGame() {
        film = new Film();
        film.start(film);

        loggedInMenu();
    }

    @Override
    public String toString() {
        return "" +
                " userType=" + getUserType() +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", mailAdress='" + getMailAdress() + '\'' ;
    }
    /**
     * Registers a new user into the system by collecting user input
     * and adding the user to the user list.
     */
    void register() {


        System.out.println("-------------KAYIT OL----------------------");

        System.out.print("---Username: ");
        String username = TryCatch.stringInput();

        //farklı bir method içinde iki password kontrolü yapılacak
        PasswordFormatControl.getAndValidatePassword();
        String acceptedPassword=  PasswordFormatControl.getPassword1();


        MailFormatControl mfk = new MailFormatControl();
        String mail = mfk.MailGirisi();
        System.out.println(mail);

        //kullanıcıdan alınan değerler ile newUser objesi üretilir
        User newUser = new RegularUser(username,acceptedPassword,mail);

        //üretilen user userListe eklenir.
        AdminUser.getUserList().putIfAbsent(newUser.getUserName(), newUser);

        if (AdminUser.getUserList().containsKey(username)) {
            System.out.println("Kaydınız başarı ile gerçekleşti.Login sayfasına yönlendiriliyorsunuz! ");

            newUser.login();
        }
    }
    /**
     * Logs out the user and terminates the application.
     */
    @Override
    void logout() {
        System.out.println("Oyuncu çıkış yaptı");
        System.exit(1);
    }
    /**
     * Logs in the user by validating the username and password.
     * Redirects to the menu if successful, or allows registration if the user is not found.
     */
    @Override
    void login() {

        System.out.println("----------------USER-----------------------");

        System.out.print("---Username: ");
        String username = TryCatch.stringInput();

        //user listede varsa password sorarak login olur.
        if (AdminUser.getUserList().containsKey(username)) {

            //username key değerindeki User regular user objesine atanır
            user = AdminUser.getUserList().get(username);
            System.out.println(user);

            //AdminUser classındaki login methodu return değerine göre ekrana çıktı yazırır
            boolean loginKontrol = user.passwordControl();
            if (loginKontrol) {
                System.out.println("Giriş başarılı");
                loggedInMenu();
            } else {
                System.out.println("Login olamadınız. Yönetici yetkisi ile şifrenize ulaşışıyor!");
                System.out.println("admin.pasword = " + user.getPassword());
                System.out.println("Tekrar deneyin!");
                user.passwordControl();
            }

        }
        //admin listede yok ise sisteme kayıt olur
        else {
            System.out.println("Kullanıcı sisteme kayıtlı değil.Kayıt ekranına yönlendiriliyorsunuz!");
            register();
        }

    }
    /**
     * Displays the main menu for the user and provides options for login, registration, or exit.
     */
    void userMenu() {
        System.out.print("----------------OYUNCU-----------------------\n" +
                "1. Giriş Yap \n" +
                "2. Kayıt Ol \n" +
                "3. Çıkış" +
                "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1->    user.passwordControl();
            case 2->    user.register();
            case 3->    System.exit(1);
            default->   userMenu();   // Repeat the menu for invalid options
        }
    }

    /**
     * Displays the menu for logged-in users and provides options to start the game, return to the main menu, or exit.
     */
    @Override
     void loggedInMenu(){
        System.out.print("----------------OYUNCU MENU-----------------------\n" +
                "1. Oyunu başlat. \n" +
                "2. Çıkış \n" +
                "3. Programı Sonlandır \n" +
                "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1->   playGame();
            case 2->   returnToMainMenu();
            case 3->   System.exit(1);
            default->  loggedInMenu();   // Repeat the menu for invalid options
        }
    }
}
