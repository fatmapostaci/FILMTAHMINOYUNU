
import java.util.Scanner;

public class Action {

    static void mainMenu() {

        System.out.print("\n" +
                "----------------FILM TAHMIN OYUNUNA HOŞGELDİNİZ--------------\n" +
                "1. Admin \n" +
                "2. Oyuncu \n" +
                "3. Çıkış \n" +
                "Seçim : ");

        int secenek = TryCatch.intInput();

        switch (secenek) {
            case 1:
                User admin= new AdminUser();
                admin.login();
                break;
            case 2:
                User user= new RegularUser();
                user.login();
                break;
            case 3:
                System.exit(0);
            default:
                mainMenu();
        }


    }

}
