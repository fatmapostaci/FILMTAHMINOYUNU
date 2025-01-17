
import java.util.*;

public class Film {

    static List<String> filmList = new ArrayList<>(List.of("hababam sınıfı", "gassal", "harry potter", "esaretin bedeli", "hıslı ve öfkeli","tosun paşa", "avatar", "batman", "god father", "akan saat", "sınırsız"));

    static Scanner scan = new Scanner(System.in);

    String secilenFilm;
    StringBuilder sifrelenmisMetin;
    int secilenFilmHarfSayisi;


    int dogruCount = 0;
    int yanlisCount = 0;
    int denemeCount = 0;
    int tahminHakki;



    public void start(Film film) {


        film.filmSec();

        boolean oyunDevam = true;

        while (oyunDevam) {
            System.out.println("Bir harf giriniz:");
            String karakter = TryCatch.stringInput();
            char harf = karakter.charAt(0);
            Set<Character> tahminEdilenHarfler = new HashSet<>(List.of(harf));


            boolean tahminDogru = film.harfKontrol(harf);

            System.out.println("************************************************");
            System.out.println("Şifrelenmiş Metin: " + film.getSifrelenmisMetin());
            System.out.println("************************************************");

            if (tahminDogru) {
                System.out.println("Doğru tahmin!");

            } else {
                System.out.println("Yanlış tahmin!");
            }


            if (!film.getSifrelenmisMetin().toString().contains("_")) {
                System.out.println("fatma değişiklik yapıyor");
                int a = TryCatch.intInput();
                System.out.println(a+ " deneme");


                Film f= new Film();
                f.filmSec();
                System.out.println("Tebrikler! Tüm harfleri bildiniz, oyunu kazandınız!");
                oyunDevam = false;

            }
            if (tahminEdilenHarfler.contains(harf)) {
                System.out.println( "fatma 2 değişiklik yapıyor deneme");
                System.out.println("Bu harfi zaten tahmin ettiniz! Daha önce tahmin edilen harf: " + tahminEdilenHarfler);

                System.out.println("içi dolu bir yer");
                System.out.println( " deneme");
                System.out.println("Bu harfi zaten tahmin ettiniz! Daha önce tahmin edilen harf: " + tahminEdilenHarfler);
                System.out.println("fatma değişiklik yapıyor");
                int a = TryCatch.intInput();
                System.out.println(a+ " deneme");
                System.out.println("Bu harfi zaten tahmin ettiniz! Daha önce tahmin edilen harf: " + tahminEdilenHarfler);
                System.out.println(a+ " deneme");
                System.out.println("Bu harfi zaten tahmin ettiniz! Daha önce tahmin edilen harf: " + tahminEdilenHarfler);
                System.out.println(a+ " deneme");
                System.out.println("Bu harfi zaten tahmin ettiniz! Daha önce tahmin edilen harf: " + tahminEdilenHarfler);

                continue; // Kullanıcıya tekrar harf girme şansı tanımak için döngünün başına dön.
            }

            bilgiEkrani();

            sonucEkranı();




        }
    }


    void createSifrelenmisMetin() {

        sifrelenmisMetin = new StringBuilder("_".repeat(secilenFilmHarfSayisi));
    }

    void sifrelenmisMetneHarfEkle(char c) {

        for (int i = 0; i < secilenFilmHarfSayisi; i++) {
            if (secilenFilm.charAt(i) == c)
                sifrelenmisMetin.setCharAt(i, c);
        }
    }

    public String filmSec() {


        System.out.print("\n-------------------OYUN BAŞLIYOR-------------------------\n\n" +
                "Lütfen Film Seçmek İçin 1-" + filmList.size() + " Arasında Bir Sayı Girin: ");
        int index = TryCatch.intInput() - 1;

        if (index < 0 || index >= filmList.size()) {

            System.out.println("Hata: Geçersiz indeks!");
            return filmSec();

        } else {

            secilenFilm = filmList.get(index);
            secilenFilm = secilenFilm.replaceAll(" ","");
            secilenFilmHarfSayisi = secilenFilmHarfSayisi();

            tahminHakki = methodTahminHakki();
            System.out.println("Filmi tahmin etmek için toplam " + " " + tahminHakki + " " + " harf hakkınız vardır.");

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

    void bilgiEkrani() {
        System.out.println("Doğru sayınız : " + " " + dogruCount);
        System.out.println("Yanlış sayınız : " + " " + yanlisCount);
        System.out.println("Geriye Kalan Hakkınız :  " + (tahminHakki - denemeCount+1  ));
    }

    void sonucEkranı() {

        if (scan.equals(secilenFilm) && (tahminHakki >= denemeCount)) {
            System.out.println(denemeCount + "Denemede kazandınız");
        } else if (denemeCount > tahminHakki) {
            System.out.println("Kaybettiniz");
            System.out.println("Tahmin etmeniz gereken film" + " " + secilenFilm + " " + "olmalıydı:=)" );
            Action.mainMenu();
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