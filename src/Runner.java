public class Runner {
    public static void main(String[] args) {

        AdminUser admin = new AdminUser("admin","123","admin@admin.com");
        RegularUser user = new RegularUser("user","123","user@gmail.com");


        user.playGame();


    }
}
