import java.util.InputMismatchException;
import java.util.Scanner;

public class KullaniciArayuzu {
    private final KiralamaSistemi sistem;
    private final Scanner scanner;

    public KullaniciArayuzu(KiralamaSistemi sistem) {
        this.sistem = sistem;
        this.scanner = new Scanner(System.in);
    }

    public void baslat() {
        int secenek = 0;

        while (secenek != 5) {
            menuGoster();
            secenek = inputNumber("Seçeneğiniz : ");
            islemiUygula(secenek);
        }
        System.out.println("Sistemden çıkılıyor...");
    }

    private void menuGoster() {
        System.out.println("""
                
                --- SCOOTER KİRALAMA SİSTEMİ ---
                1 - Tüm araçları listele
                2 - Araç ekle
                3 - Müsaitlik sorgulama
                4 - Araç kirala
                5 - Çıkış
                """);
    }

    private void islemiUygula(int secenek) {
        switch (secenek) {
            case 1 -> sistem.tumAraclariListele();
            case 2 -> aracEklemeEkrani();
            case 3 -> {
                System.out.println("Sorgulamak istediğiniz aracın ID'si");
                String id = inputString("ID : ");
                sistem.musaitlikSorgulama(id);
            }
            case 4 -> {
                System.out.println("--- Araç Kiralama ---");
                String id = inputString("Kiralamak istediğiniz aracın ID : ");
                if (!sistem.musaitlikSorgulama(id)) break;
                int sure = inputNumber("Kaç dakika sürmek istiyorsunuz : ");

                try {
                    sistem.aracKirala(id, sure);
                } catch (YetersizSarjException e) {
                    System.out.println("Hata : " + e.getMessage());
                }
            }
        }
    }

    private void aracEklemeEkrani() {
        System.out.println("--- Araç Ekleme ---");
        int tur = inputNumber("""
                --- Eklemek istediğiniz Scooter Türü ---
                1 - Standart Scooter
                2 - Pro Scooter
                Seçiminiz:""");

        // Fabrikadan isim sözlüğünü çekiyoruz
        String tip = ScooterFactory.getTipAdi(tur);

        if (tip == null) {
            System.out.println("Hatalı seçenek girdiniz!");
        } else {
            String id = inputString("Araç ID : ");
            if (!sistem.idKontrol(id)) {
                // Fabrikaya üretim emri veriyoruz
                Arac yeniArac = ScooterFactory.scooterUret(tip, id, 100, "Merkez", AracDurumu.MUSAIT);
                if (yeniArac != null) {
                    sistem.aracEkle(yeniArac);
                    System.out.println("Araç başarıyla sisteme eklendi.");
                }
            }
        }
    }

    private int inputNumber(String mesaj) {
        while (true) {
            try {
                System.out.print(mesaj);
                int deger = scanner.nextInt();
                scanner.nextLine();
                return deger;
            } catch (InputMismatchException e) {
                System.err.println("Geçersiz karakter !");
                scanner.nextLine();
            }
        }
    }

    private String inputString(String mesaj) {
        while (true) {
            try {
                System.out.print(mesaj);
                return scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Geçersiz karakter !");
            }
        }
    }
}