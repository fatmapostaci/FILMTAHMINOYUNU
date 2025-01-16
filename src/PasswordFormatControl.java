import java.util.Scanner;

public class PasswordFormatControl {
    static String password1;
    static String password2;

    static boolean isPasswordValid = false;

    public static void getAndValidatePassword() {
        while (!isPasswordValid) {
            setPassword1();

            if (isPasswordAllInteger()) {
                System.out.println("Parola sadece sayılardan oluşamaz.");
                continue; // Döngünün başına dön
            }

            if (!isLongerThanEight()) {
                System.out.println("Karakter uzunluğu en az 8 olmalı.");
                continue; // Döngünün başına dön
            }

            isPasswordValid = true; // Eğer buraya ulaşıldıysa, password1 geçerli
        }

        setPassword2();

        if (isPasswordMatches()) {
            System.out.println("Şifre başarıyla kaydedildi!");
        } else {
            System.out.println("Şifreler eşleşmiyor. Lütfen tekrar deneyin.");
            isPasswordValid = false; // Tekrar baştan başlat
            getAndValidatePassword();
        }
    }

    public static String getPassword1() {
        return password1;
    }

    public static String getPassword2() {
        return password2;
    }

    public static void setPassword1() {
        System.out.print("---Password1: ");
        password1 = TryCatch.scan.next();
    }

    public static void setPassword2() {
        System.out.print("---Password Tekrar: ");
        password2 = TryCatch.scan.next();
    }

    public static boolean isPasswordMatches() {
        return password1.equals(password2);
    }

    public static boolean isPasswordAllInteger() {
        // Parolanın her bir karakterini kontrol et
        for (int i = 0; i < password1.length(); i++) {
            if (!Character.isDigit(password1.charAt(i))) {
                return false; // Eğer sayı olmayan bir karakter bulursa false döner
            }
        }
        return true; // Eğer tüm karakterler sayı ise true döner
    }

    public static boolean isLongerThanEight() {
        return password1.length() >= 8;
    }
}
