
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        KiralamaSistemi sistem = new KiralamaSistemi();


        sistem.verileriYukle();
        int secenek = 0;
        while (secenek != 5){
            System.out.println("""
                1 - Tüm araçları listele
                2 - Araç ekle
                3 - Müsaitlik sorgulama
                4 - Araç kirala
                5 - Çıkış
                """);
            secenek = inputNumber(scanner, "Seçeneğiniz : ");

            switch (secenek){
                case 1 ->{

                    sistem.tumAraclariListele();
                }
                case 2 ->{
                    String id;
                    System.out.println("--- Araç Ekleme ---");
                    int tur = inputNumber(scanner, """
                        --- Eklemek istediğiniz Scooter Türü ---
                        1 - Standart Scooter
                        2 - Pro Scooter
                        """);
                    if(tur == 1){
                        id = inputString(scanner, "Araç ID : ");
                        if (sistem.idKontrol(id)){break;}
                        sistem.aracEkle(new StandartScooter(id,100,"Merkez",false));
                        System.out.println("Araç başarıyla eklendi");
                    }
                    else if (tur == 2) {
                        id = inputString(scanner, "Araç ID : ");
                        if (sistem.idKontrol(id)){break;}
                        sistem.aracEkle(new ProScooter(id,100,"Merkez",false));
                        System.out.println("Araç başarıyla eklendi");
                    }
                    else{
                        System.out.println("Hatalı seçenek");
                    }
                }

                case 3 ->{
                    System.out.println("Sorgulamak istediğiniz aracın ID si");
                    String id = inputString(scanner, "ID : ");
                    sistem.musaitlikSorgulama(id);
                }

                case 4 ->{
                    System.out.println("--- Araç Kiralama ---");
                    String id = inputString(scanner, "Kiralamak istediğiniz aracın ID : ");
                    if (!sistem.musaitlikSorgulama(id)) break;
                    int sure = inputNumber(scanner, "Kaç dakika sürmek istiyorsunuz : ");

                    try {
                        sistem.aracKirala(id,sure);
                    }
                    catch (YetersizSarjException e){
                        System.out.println("Hata : "+ e.getMessage());
                    }
                }
                case 5->sistem.verileriKaydet();




            }
        }


    }

    public static int inputNumber(Scanner scanner, String mesaj){
        while (true){
            try {
                System.out.print(mesaj);
                int deger;
                deger = scanner.nextInt();
                scanner.nextLine();
                return deger;
            }
            catch (InputMismatchException e){
                System.err.println("Geçersiz karakter !");
                scanner.nextLine();
            }
        }
    }


    public static String inputString(Scanner scanner, String mesaj){
        while (true){
            try {
                System.out.print(mesaj);
                String veri;
                veri = scanner.nextLine();
                return veri;
            }
            catch (InputMismatchException e){
                System.err.println("Geçersiz karakter !");
            }
        }
    }



}