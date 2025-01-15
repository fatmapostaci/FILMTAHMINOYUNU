public class Runner {
    public static void main(String[] args) {



        RegularUser user1 = new RegularUser("asd","asd","asd");
        RegularUser user2 = new RegularUser("xcv","xcv","xcv");

        AdminUser adm = new AdminUser("admin","admin","admin@admin.com");

        adm.addUserToHashMap(user1);
        adm.addUserToHashMap(user2);

        System.out.println(adm.getUserList());


        Action.mainMenu();

        System.out.println(Action.admin.toString());


        // user.playGame();


    }
}
