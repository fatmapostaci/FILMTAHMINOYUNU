
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
        return " UserType: "  + getUserType() + "\tUsername: " + getUserName() + "\tPassword: " + getPassword() + "\tMail Adress: " + getMailAdress();
    }
    /**
     * Registers a new user into the system by collecting user input
     * and adding the user to the user list.
     */
    void register() {


        System.out.println("-------------KAYIT OL----------------------");

        System.out.print("---Username: ");
        String username = TryCatch.stringInput();

        //farklı bir method içinde iki password kullanıcıdan alınıp, eşleştiği kontrolü ve uygun koşulları sağladığı kontrolü yapılacak
        PasswordFormatControl.getAndValidatePassword1and2();
        String acceptedPassword = PasswordFormatControl.getPassword1();

        //MailFormatControl classından static method MailGirisi() çağırılarak, mailin standart koşulları sağladığı sorgulanır
        // mail inputu koşullara uygun olarak kullanıcıdan alınır and assigned to string variable.
        MailFormatControl mfk = new MailFormatControl();
        String mail = mfk.MailGirisi();

        //kullanıcıdan alınan değerler ile newUser objesi üretilir
        User newUser = new RegularUser(username, acceptedPassword, mail);

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
        System.exit(0);
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

        //user listede varsa, ancak ADMIN ise anamenüye yönlendirir.
        if (AdminUser.getUserList().containsKey(username) && AdminUser.getUserList().get(username).getUserType() == UserType.ADMIN) {
            System.out.println("Admin kullanıcısı ile Yönetim rolü için Admin sayfasından login olmalısınız! ");
            user.returnToMainMenu();
        }

        //user listede varsa  ve user REGULARUSER ise password sorarak login olur.
        if (AdminUser.getUserList().containsKey(username) && AdminUser.getUserList().get(username).getUserType() == UserType.REGULARUSER) {

            //username key değerindeki User regular user objesine atanır
            user = AdminUser.getUserList().get(username);

            //password belirlenir
            String password1 = user.getPassword();

            //User classındaki passwordControl methodu return değerine göre ekrana çıktı yazırır ve uygun sayfaya yönlendirilir
            boolean loginKontrol = user.passwordControl(password1);
            if (loginKontrol) {
                System.out.println("Giriş başarılı");
                user.loggedInMenu();
            } else {
                System.out.println("Login olamadınız.Ana Menüye yönlendiriliyorsunuz! ");
                user.returnToMainMenu();
            }

        }
        //user listede yok ise sisteme kayıt olur
        else if (!AdminUser.getUserList().containsKey(username)) {
            System.out.println("Kullanıcı sisteme kayıtlı değil.Kayıt ekranına yönlendiriliyorsunuz!");
            register();
        }
        else {
            System.out.println("Hata mevcut.Sorun çözülecek, program sonlandırıldı");
            terminateTheProgram();
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
            case 1 -> user.passwordControl(user.getPassword());
            case 2 -> user.register();
            case 3 -> user.logout();
            default -> userMenu();   // Repeat the menu for invalid options
        }
    }

    /**
     * Displays the menu for logged-in users and provides options to start the game, return to the main menu, or exit.
     */
    @Override
    void loggedInMenu() {
        System.out.print("----------------OYUNCU MENU-----------------------\n" +
                "1. Oyunu başlat. \n" +
                "2. Çıkış \n" +
                "3. Programı Sonlandır \n" +
                "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1 -> playGame();
            case 2 -> returnToMainMenu();
            case 3 -> logout();
            default -> loggedInMenu();   // Repeat the menu for invalid options
        }
    }
}
