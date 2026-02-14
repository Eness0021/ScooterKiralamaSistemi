import java.util.ArrayList;
import java.util.List;

public class KiralamaSistemi{
    List<Arac> araclar = new ArrayList<>();


    public void aracEkle(Arac arac){
        araclar.add(arac);
    }

    public void aracKirala(String id, int sure) throws YetersizSarjException{
        for (Arac a : araclar){
            if (a.aracId.equalsIgnoreCase(id)){
                if(a.getSarjYuzdesi() < 20){
                    throw new YetersizSarjException("Hata : Aracın şarjı çok düşük");
                }
                if(!a.KiradaMi()){
                    System.out.println("Araç kiralandı");
                    System.out.print("Ücret : " + a.ucretHesapla(sure));
                    System.out.println();
                    a.setKiradaMi(true);
                }
                else {
                    System.out.println("Hata : Araç zaten kirada");
                }
                return;
            }
        }
        System.out.println("Hata : Araç bulunamadı");
    }

    public void tumAraclariListele(){
        for (Arac arac : araclar){
            System.out.println(arac);
        }
    }

    public boolean idKontrol(String id){
        for (Arac arac : araclar){
            if(id.equalsIgnoreCase(arac.aracId)){
                System.out.println("Araç zaten mevcut");
                return true;
            }
        }
        return false;
    }

    public boolean musaitlikSorgulama(String id){
        for (Arac arac : araclar){
            if (arac.aracId.equalsIgnoreCase(id)){
                if (arac.KiradaMi() || arac.getSarjYuzdesi() < 20){
                    System.out.println("Araç şuan müsait değil");
                    return false;
                }
                else{
                    System.out.println("Araç şuan müsait");
                    return true;
                }
            }
        }
        System.out.println("girilen id ye sahip araç bulunamadı!");
        return false;
    }

}
