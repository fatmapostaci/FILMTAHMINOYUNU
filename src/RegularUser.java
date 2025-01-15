public class RegularUser extends User {

    final int maxLoginAttempt = 3;
    private int loginAttempts;
    Film film;

    static User user;

    RegularUser(String userName, String password, String mailAdress) {
        super(userName, password, mailAdress, UserType.REGULARUSER);
    }

    RegularUser() {
        super();
    }

    public void playGame() {
        film = new Film();
        film.start(film);
    }

    @Override
    public String toString() {
        return "" +
                " userType=" + getUserType() +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", mailAdress='" + getMailAdress() + '\'' ;
    }

    void register() {

        AdminUser.getUserList().putIfAbsent(user.getUserName(), user);
    }

    @Override
    void logout() {
        System.out.println("Oyuncu çıkış yaptı");
        System.exit(1);
    }

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
            user.register();
            //username parametreli constructor çağırılır
            //admin = new AdminUser(username);
            //yeni admin eklemek için buradan uygun method çağırılarak geliştirme yapılabilir
        }

    }
    static void userMenu() {
        System.out.print("----------------OYUNCU-----------------------\n" +
                "1. Giriş Yap \n" +
                "2. Kayıt Ol \n" +
                "3. Çıkış" +
                "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1:
                user.passwordControl();
            case 2:
                user.register();
            case 3:
                System.exit(1);
            default:
                userMenu();
        }
    }
    @Override
     void loggedInMenu(){
        System.out.print("----------------OYUNCU MENU-----------------------\n" +
                "1. Oyunu başlat. \n" +
                "2. Çıkış \n" +
                "3. Ana Menüye Dön \n" +
                "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1:
                  playGame();
            case 2:
                System.exit(1);
            case 3:
                returnToMainMenu();
            default:
                loggedInMenu();
        }
    }
}
