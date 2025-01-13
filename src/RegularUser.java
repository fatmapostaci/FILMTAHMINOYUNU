public class RegularUser extends User {

    final int maxLoginAttempt=3;
    private int loginAttempts;
    Film film;

    public RegularUser(String userName, String password, String mailAdress) {
        super(userName, password, mailAdress, UserType.STANDARTUSER);
    }


    @Override
    public boolean login(String username, String password) {
        return true;
    }

    @Override
    public void logout(String username) {

    }

    public int register(){

        return 0;
    }

    public void playGame(){

        film = new Film();
        film.filmSec();


        System.out.println("sifrelenmisMetin = " + film.getSifrelenmisMetin());
        System.out.println("f.harfKontrol('a') = " + film.harfKontrol('a'));
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

}
