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

        System.out.println("----------------ADMIN GIRISI-----------------------" +
                "1. Film Listesini Düzenle" +
                "2. Kullanıcı Sil" +
                "3. " +
                "4. " +
                "5. ");


        int secenek = scanf.nextInt();

        admin.listUsers();
        admin.deleteFilmFromList();
    }

    void userMenu(){

    }
}
