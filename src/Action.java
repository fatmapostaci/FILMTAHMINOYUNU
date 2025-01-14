import java.util.Scanner;

public class Action {

    AdminUser admin;
    RegularUser user;

    static Scanner scanf = new Scanner(System.in);


    void menu(){

        System.out.println("Kullanıcı türü girin: (Admin/User)");

        String type = scanf.next().substring(0,1).toUpperCase();

       if( type.equals("A") ) {

           admin = new AdminUser();
           System.out.println("----------------ADMIN GIRISI-----------------------");

           System.out.println("username: ");
           admin.setUserName( scanf.next());

           System.out.println("pasword: ");
           admin.setPassword( scanf.next());

           System.out.println("mail address: ");
           admin.setMailAdress( scanf.next());   //mailformat eklenecek


           adminMenu();

       }
       else if( type.equals("U") ){

           user = new RegularUser();
           System.out.println("----------------KULLANICI GIRISI-----------------------");
           System.out.println("username: ");
           user.setUserName( scanf.next());

           System.out.println("pasword: ");
           user.setPassword( scanf.next());

           System.out.println("mail address: ");
           user.setMailAdress( scanf.next());   //mailformat eklenecek



           userMenu();

       }
    }

    void adminMenu(){

        System.out.println("----------------ADMIN MENU-----------------------" +
                "1. Film Listesini Düzenle" +
                "2. Kullanıcı Sil" +
                "3. " +
                "4. " +
                "5. Ana Menuye Dön ");


        int secenek = scanf.nextInt();

        switch (secenek){
            case 1: admin.updateFilmList();

            case 2: admin.deleteUser();

            case 3:

            case 4:

            case 5: admin.returnToMainMenu();

        }

    }

    void userMenu() {

        System.out.println("----------------USER MENU-----------------------" +
                "1. Kayıtlı değilse kayıtlı olma" +
                "2. Kayıtlı işe oyuna başlayabilme" +
                "3. Logout" +
                "4. " +
                "5. ");

        int secenek = scanf.nextByte();

        switch (secenek) {
            case 1:
                user.register();

            case 2:
                user.login();

            case 3:
                user.playGame();
        }
    }
}
