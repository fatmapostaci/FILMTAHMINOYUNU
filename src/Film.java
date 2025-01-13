
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Film {

    static Scanner scan = new Scanner(System.in);

    static List<String> flimler = new ArrayList<>(List.of("ucuz roman", "esaretin bedeli", "hızlı ve öfkeli", "batman", "god father", "akan saat", "sınırsız"));


    String flimSecimi(ArrayList<String> list, int index) {
        System.out.print("Lütfen bir film seçmek için 1-" + flimler.size() + " arasında bir numara girin: ");
        int secim = scan.nextInt();

        if (index < 0 || index >= list.size()) {
            return "Hata: Geçersiz indeks!";
        }
        String secilenFilm = flimler.get(index);

        return secilenFilm;
    }

    int secilenFlimHarfSayısı(String secilenFilm) {

        int harfSayısı = secilenFilm.length();

        return harfSayısı;
    }

    int tahminHakkı;

    int tahminHakkı(int harfSayısı) {

        int tahminHakkı = harfSayısı + harfSayısı;

        return tahminHakkı;
    }

    int doğruCount = 0;
    int yanlışCount = 0;
    int denemeCount = 0;

    void HarfKontrol(char harf, String secilenFilm) {

        String harfString = Character.toString(harf);

        if (secilenFilm.contains(harfString)) {
            System.out.println("Film adında '" + harf + "' harfi bulunmaktadır.");
            doğruCount++;
            denemeCount++;
        } else {
            System.out.println("Film adında '" + harf + "' harfi bulunmamaktadır.");
            yanlışCount++;
            denemeCount++;
        }

    }

    void bilgiEkranı() {

        System.out.println("Toplam denemeniz : " + " " + denemeCount);
        System.out.println("Doğru sayınız : " + " " + doğruCount);
        System.out.println("Yanlış sayınız : " + " " + yanlışCount);
        System.out.println("Geriye Kalan Hakkınız :  " + (tahminHakkı - denemeCount));
    }

    Scanner secilenFilm = null;

    void sonucEkranı() {

        if (scan.equals(secilenFilm) && (tahminHakkı >= denemeCount)) {
            System.out.println(denemeCount + "Denemede kazandınız");
        } else if (denemeCount > tahminHakkı) {
            System.out.println("Kaybettiniz");
            System.out.println("Tahmin etmeniz gereken flim olmalıydı :" + secilenFilm);
        }

    }


}