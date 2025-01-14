public class Runner {
    public static void main(String[] args) {



        RegularUser user1 = new RegularUser("asd","asd","asd");
        RegularUser user2 = new RegularUser("xcv","xcv","xcv");

        AdminUser adm = new AdminUser("admin","admin","admin");

        adm.addUserToHashMap(user1);
        adm.addUserToHashMap(user2);

        System.out.println(adm.getUserList());


        Action act = new Action();
      act.menu();

        System.out.println(act.admin.toString());


        // user.playGame();


    }
}
