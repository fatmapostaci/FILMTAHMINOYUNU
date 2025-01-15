public class Runner {
    public static void main(String[] args) {


        //admin referansı ile listeye adöin kayıt işlemi
        AdminUser adm = new AdminUser("admin","admin","admin@admin.com");

        RegularUser user1 = new RegularUser("asd","asd","asd");
        RegularUser user2 = new RegularUser("xcv","xcv","xcv");


        //sadece admin referansı olan obje listeye kullanıcı ekleyebilir
        adm.addUserToHashMap(user1);
        adm.addUserToHashMap(user2);
        adm.addUserToHashMap(adm);


        System.out.println(AdminUser.getUserList());

        Action.mainMenu();


    }
}
