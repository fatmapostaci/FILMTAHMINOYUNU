
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Film {

    static List<String> filmList = new ArrayList<>(List.of("ucuz roman", "esaretin bedeli", "hızlı ve öfkeli", "batman", "god father", "akan saat", "sınırsız"));


    static Scanner scan = new Scanner(System.in);

    String secilenFilm;
    StringBuilder sifrelenmisMetin;
    int secilenFilmHarfSayisi;


    int dogruCount = 0;
    int yanlisCount = 0;
    int denemeCount = 0;
    int tahminHakki;



    void createSifrelenmisMetin() {

        sifrelenmisMetin = new StringBuilder("_".repeat(secilenFilmHarfSayisi));
    }

    void sifrelenmisMetneHarfEkle(char c) {

        for (int i = 0; i < secilenFilmHarfSayisi; i++) {
          if(secilenFilm.charAt(i) == c)
              sifrelenmisMetin.setCharAt(i, c);
        }
    }

    public String filmSec() {


        System.out.print("Lütfen bir film seçmek için 1-" + filmList.size() + " arasında bir numara girin: ");
        int index = scan.nextInt() - 1;

        if (index < 0 || index >= filmList.size()) {

            System.out.println("Hata: Geçersiz indeks!");
            return filmSec();

        } else {

            secilenFilm = filmList.get(index);
            System.out.println("secilenFilm = " + secilenFilm);

            secilenFilmHarfSayisi = secilenFilmHarfSayisi();
            System.out.println("secilenFilmHarfSayisi = " + secilenFilmHarfSayisi);

            tahminHakki = methodTahminHakki();
            System.out.println("tahminHakki = " + tahminHakki);

            createSifrelenmisMetin();

            return secilenFilm;
        }
    }

    int secilenFilmHarfSayisi() {

        return secilenFilm.length();
    }

    int methodTahminHakki() {

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

    public int getTahminHakki() {
        return tahminHakki;
    }

    public void setTahminHakki(int tahminHakki) {
        this.tahminHakki = tahminHakki;
    }

    public String getSecilenFilm() {
        return secilenFilm;
    }

    public void setSecilenFilm(String secilenFilm) {
        this.secilenFilm = secilenFilm;
    }

    public StringBuilder getSifrelenmisMetin() {
        return sifrelenmisMetin;
    }

    public void createSifrelenmisMetin(StringBuilder sifrelenmisMetin) {
        this.sifrelenmisMetin = sifrelenmisMetin;
    }

    public int getSecilenFilmHarfSayisi() {
        return secilenFilmHarfSayisi;
    }

    public void setSecilenFilmHarfSayisi(int secilenFilmHarfSayisi) {
        this.secilenFilmHarfSayisi = secilenFilmHarfSayisi;
    }



}