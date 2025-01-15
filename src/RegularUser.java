import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegularUser extends User {

    final int maxLoginAttempt = 3;
    private int loginAttempts;
    Film film;

    List<String> registerList;
    List<String> loginList;
    int sifreDenemeHakki = 3;

    static Scanner scanuser = new Scanner(System.in);

    public RegularUser() {
        super(UserType.REGULARUSER);
    }

    public RegularUser(String userName, String password, String mailAdress) {
        super(userName, password, mailAdress, UserType.REGULARUSER);
    }


    @Override
    public boolean login() {
        System.out.println("----------LOGİN----------");
        System.out.println("Harf ve sayılardan olusan Kullanıcı adını giriniz");
        String userNameLogin = scanuser.next();
        System.out.println("Harf ve sayılardan olusan passwordunuzu giriniz");
        String userPasswordLogin = scanuser.next();
        new ArrayList<>(List.of(userNameLogin, userPasswordLogin));
        while (sifreDenemeHakki == 0) {
            if (loginList.equals(registerList)) {
                System.out.println("Giriş başarılçöı");
                return true;
            } else {
                System.out.println("Yanlış kullanıcı adı veya şifre tekrar deneyiniz");
                sifreDenemeHakki--;
            }
            return false;
        }
        System.out.println("3 defa yanlış girdiniz");
        return false;
    }


    @Override
    public boolean logout() {

        return false;
    }

    public List<String> register() {
        System.out.println("----------REGİSTER----------");

        System.out.println("Kayıt etmek istediğiniz harf ve sayılardan oluşan kullanıcı adını giriniz");
        String userNameRegister = scanuser.next();
        System.out.println("Harf ve sayılardan oluşan passwordunuzu giriniz");
        String passwordRegister = scanuser.next();
        new ArrayList<>(List.of(userNameRegister, passwordRegister));
        return registerList;
    }

    public void playGame() {

        film = new Film();
        film.filmSec();


        System.out.println("sifrelenmisMetin = " + film.getSifrelenmisMetin());

        char harf='a';
        System.out.println("f.harfKontrol('a') = " + film.harfKontrol(harf));
        System.out.println("sifrelenmisMetin = " + film.getSifrelenmisMetin());
        System.out.println("f.harfKontrol('a') = " + film.harfKontrol('b'));
        System.out.println("sifrelenmisMetin = " + film.getSifrelenmisMetin());
    }


    public int getMaxLoginAttempt() {
        return maxLoginAttempt;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    @Override
    public String toString() {
        return "RegularUser{" +

                ", film=" + film +
                ", userType=" + getUserType() +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", mailAdress='" + getMailAdress() + '\'' +
                ", userType=" + getUserType() +
                '}';
    }
}
