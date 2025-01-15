import java.util.Scanner;

public class Action {

    static AdminUser admin;
    static RegularUser user;

    static Scanner scanf = new Scanner(System.in);


   static void mainMenu(){

        System.out.print("\n" +
                    "----------------FILM TAHMIN OYUNUNA HOŞGELDİNİZ--------------\n" +
                    "---Oyuncu yada Admin olarak edebilirsiniz! \n" +
                    "---Programı sonlandırmak için herhangibir tuşa basabilirsiniz! \n" +
                    "---Lütfen kullanıcı türü girin \n" +
                    "---(Admin/Oyuncu/Çıkış) :  ");

        String type = scanf.next().substring(0,1).toUpperCase();

       if( type.equals("A") ) {

           admin = new AdminUser();
           System.out.println("----------------ADMIN-----------------------");

           System.out.print("---Username: ");
           admin.setUserName( scanf.next());

           System.out.print("---Pasword: ");
           admin.setPassword( scanf.next());

           //System.out.print("---Mail Address: ");
           //admin.setMailAdress( scanf.next());   //mailformat eklenecek


           adminMenu();

       }
       else if( type.equals("U") || type.equals("O")){

           user = new RegularUser();
           System.out.print("----------------OYUNCU-----------------------\n" +
                            "---Sisteme giriş yapın! \n" +
                            "---Kayıtlı değilseniz kayıt sayfasına yönlendirileceksiniz. \n");
           System.out.print("---Username: ");
           user.setUserName( scanf.next());

           System.out.print("---Pasword: ");
           user.setPassword( scanf.next());

           //System.out.print("---Mail address: ");
           //user.setMailAdress( scanf.next());   //mailformat eklenecek


//burada kullanıcı sistemde var mı yok mu kontrolü olmalı, var ıser userMenu çağırılır.
           userMenu();

       }
       /**
        * Programı sonlandırır.
        */
       else {
           System.exit(0);
       }
    }

    static void adminMenu(){

        System.out.print("----------------ADMIN MENU-----------------------\n" +
                "1. Film Listesini Düzenle \n" +
                "2. Kullanıcı Sil \n" +
                "3. Çıkış \n" +
                "4. \n" +
                "5. Ana Menuye Dön \n" +
                "Seçim : ");


        int secenek = scanf.nextInt();

        switch (secenek){
            case 1: admin.updateFilmList();

            case 2: admin.deleteUser();

            case 3: admin.logout();

            case 4:  secenek=0;

            case 5: admin.returnToMainMenu();

            default: secenek=0;
        }

    }

    static void userMenu() {

        System.out.print("----------------OYUNCU MENU-----------------------\n" +
                "1. Kayıtlı değilse kayıtlı olma \n" +
                "2. Kayıtlı işe oyuna başlayabilme \n" +
                "3. Çıkış \n" +
                "4. \n" +
                "5. \n" +
                "Seçim : ");

        int secenek = scanf.nextByte();

        switch (secenek) {
            case 1:
                user.register();

            case 2:
                user.playGame();

            case 3:
                Action.mainMenu();
        }
    }
}
