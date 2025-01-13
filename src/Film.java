
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Film {

    static Scanner scan = new Scanner(System.in);
    static List<String> filmList = new ArrayList<>(List.of("ucuz roman", "esaretin bedeli", "hızlı ve öfkeli", "batman", "god father", "akan saat", "sınırsız"));
    int dogruCount = 0;
    int yanlisCount = 0;
    int denemeCount = 0;
    static int tahminHakki;
    static String arananFim;
    static StringBuilder sifrelenmisMetin;
    static int secilenFilmHarfSayisi;
    static String secilenFilm;
//kodu kontrol edebilmek için geçici main methodu yazdım. maine verileri alabilmek için variable ları static olarak güncellemek zorunda kaldım.
    //main silinirken değişkenlerdeki static silinecek

    public static void main(String[] args) {

        System.out.println("filmList = " + filmList);

        Film f = new Film();

        secilenFilm = f.filmSecimi();
        System.out.println("secilenFilm = " + secilenFilm);

        secilenFilmHarfSayisi = f.secilenFilmHarfSayisi();
        System.out.println("secilenFilmHarfSayisi = " + secilenFilmHarfSayisi);

        tahminHakki = f.tahminHakki();
        System.out.println("tahminHakki = " + tahminHakki);

        f.setSifrelenmisMetin();
        System.out.println("sifrelenmisMetin = " + sifrelenmisMetin);
        System.out.println("f.harfKontrol('a') = " + f.harfKontrol('a'));
        System.out.println("sifrelenmisMetin = " + sifrelenmisMetin);
        System.out.println("f.harfKontrol('a') = " + f.harfKontrol('b'));
        System.out.println("sifrelenmisMetin = " + sifrelenmisMetin);


    }

    void setSifrelenmisMetin() {

        sifrelenmisMetin = new StringBuilder("_".repeat(secilenFilmHarfSayisi));
    }

    void sifrelenmisMetneHarfEkle(char c) {

        for (int i = 0; i < secilenFilmHarfSayisi; i++) {
          if(secilenFilm.charAt(i) == c)
              sifrelenmisMetin.setCharAt(i, c);
        }
    }

    String filmSecimi() {

        System.out.print("Lütfen bir film seçmek için 1-" + filmList.size() + " arasında bir numara girin: ");
        int index = scan.nextInt() - 1;

        if (index < 0 || index >= filmList.size()) {
            System.out.println("Hata: Geçersiz indeks!");
            return filmSecimi();
        } else {
            return filmList.get(index);
        }
    }

    int secilenFilmHarfSayisi() {

        return secilenFilm.length();
    }

    int tahminHakki() {

        return secilenFilmHarfSayisi * 2;
    }

    boolean harfKontrol(char harf) {

        String harfString = Character.toString(harf);

        if (secilenFilm.contains(harfString)) {
            System.out.println("Film adında '" + harf + "' harfi bulunmaktadır.");
            dogruCount++;
            denemeCount++;
            sifrelenmisMetneHarfEkle(harf);
            return true;

        } else {
            System.out.println("Film adında '" + harf + "' harfi bulunmamaktadır.");
            yanlisCount++;
            denemeCount++;

            return false;
        }
    }

    void bilgiEkranı() {

        System.out.println("Toplam denemeniz : " + " " + denemeCount);
        System.out.println("Doğru sayınız : " + " " + dogruCount);
        System.out.println("Yanlış sayınız : " + " " + yanlisCount);
        System.out.println("Geriye Kalan Hakkınız :  " + (tahminHakki - denemeCount));
    }

    void sonucEkranı() {

        if (scan.equals(secilenFilm) && (tahminHakki >= denemeCount)) {
            System.out.println(denemeCount + "Denemede kazandınız");
        } else if (denemeCount > tahminHakki) {
            System.out.println("Kaybettiniz");
            System.out.println("Tahmin etmeniz gereken flim olmalıydı :" + secilenFilm);
        }

    }


}