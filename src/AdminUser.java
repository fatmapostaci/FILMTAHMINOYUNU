import java.util.HashMap;
import java.util.Objects;

public class AdminUser extends User {

    static User admin;
    private static HashMap<String, User> userList = new HashMap<>();

    AdminUser(String userName, String password, String mailAdress) {
        super(userName, password, mailAdress, UserType.ADMIN);
    }

    public AdminUser() {
        super();
    }

    public void addUserToHashMap(User user) {


            getUserList().putIfAbsent(user.getUserName(), user);

            //System.out.println("Kullanıcı ekleme yetkiniz yok!");


    }

    void deleteUser() {
    }

    void updateFilmList() {

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
    @Override
     void loggedInMenu() {
        System.out.print("----------------ADMIN MENU-----------------------\n" +
                "1. Film Listesine Film EKLE) \n" +
                "2. Film Listesinden Film SİL \n" +
                "3. Kullanıcı Sil \n" +
                "4. AnaMenüye Dön \n" +
                "5. Çıkış \n" +
                "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1:
                addFilmToList();
            case 2:
                deleteFilmFromList();
            case 3:
                deleteUser();
            case 4:
                returnToMainMenu();
            case 5:
                logout();
            default:
                //farklı bir seçenek girerse menü tekrar gelir
                loggedInMenu();
        }

    }

    private void deleteFilmFromList() {
        System.out.println("----------------FILM SİL----------------------");

        //varolan filmList listesini foreach ile yazdırır
        for(String s : Film.filmList){
            System.out.print(s + "\t");
        }
        //silinmek istenen film adı kullanıcıdan alınır
        System.out.println("Silinecek Film Adı : ");
        String film = TryCatch.stringInput();

        boolean silindiMi=false;

        //silinmek istenen film, filmList içinde var ise remove edilir
        if ( Film.filmList.contains(film)){
            silindiMi = Film.filmList.remove(film);
        }
        else{
            System.out.println("Listede bu film mevcut değil!");
        }

        if(silindiMi){
            System.out.println("Başarıyla silindi");
        }

    }

    private void addFilmToList() {
        System.out.println("----------------FILM EKLE----------------------");

        //varolan filmList listesini foreach ile yazdırır
        for(String s : Film.filmList){
            System.out.print(s + "\t");
        }
        //eklenmek istenen film adı kullanıcıdan alınır
        System.out.println("Eklenecek Film Adı : ");
        String film = TryCatch.stringInput();

        boolean eklendiMi=false;

        //eklennmek istenen film, filmList içinde yok ise eklenir
        if ( Film.filmList.contains(film)){
            System.out.println("Listede bu film zaten mevcut!");
        }
        else{
            eklendiMi =Film.filmList.add(film);
        }

        if(eklendiMi){
            System.out.println("Başarıyla eklendi");
        }
    }

//public void listUsers() {}
//public void addFilmToList() {}

}
