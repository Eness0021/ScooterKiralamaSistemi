import java.util.Map;

public class KiralamaSistemi {

    private static final int MIN_SARJ_SINIRI = 10;
    private final IVeriKaynagi veriKaynagi;
    private final Map<String, Arac> araclar;

    public KiralamaSistemi(IVeriKaynagi veriKaynagi){
        this.veriKaynagi = veriKaynagi;
        this.araclar = veriKaynagi.verileriYukle();
    }

    public void aracEkle(Arac arac){
        veriKaynagi.aracKaydet(arac);
        araclar.put(arac.getAracId(), arac);
    }

    public void aracKirala(String id, int sure) throws YetersizSarjException {
        if (sure <= 0) {
            System.err.println("Hata: Kiralama süresi 0 veya negatif olamaz!");
            return;
        }

        Arac a = araclar.get(id);

        if (a == null) {
            System.out.println("Hata : Araç bulunamadı");
            return;
        }

        if (a.getSarjYuzdesi() < MIN_SARJ_SINIRI) {
            a.setDurum(AracDurumu.SARJI_YETERSIZ);
            veriKaynagi.durumGuncelle(a.getAracId(), AracDurumu.SARJI_YETERSIZ);
            throw new YetersizSarjException("Aracın şarjı sınırın altında, kiralanamaz!");
        }

        if (a.getDurum() != AracDurumu.MUSAIT) {
            System.out.println("Hata : Araç şu an müsait değil (" + a.getDurum() + ")");
            return;
        }

        System.out.println("Araç kiralandı. Ücret : " + a.ucretHesapla(sure));
        a.setDurum(AracDurumu.KIRADA);
        veriKaynagi.durumGuncelle(a.getAracId(), AracDurumu.KIRADA);
    }

    public boolean musaitlikSorgulama(String id) {
        Arac arac = araclar.get(id);

        if (arac == null) {
            System.out.println("Girilen ID'ye sahip araç bulunamadı!");
            return false;
        }

        if (arac.getDurum() != AracDurumu.MUSAIT || arac.getSarjYuzdesi() < MIN_SARJ_SINIRI) {
            System.out.println("Araç şu an müsait değil. Durum: " + arac.getDurum());
            return false;
        }

        System.out.println("Araç şu an müsait.");
        return true;
    }

    public void tumAraclariListele(){
        for (Arac arac : araclar.values()){
            System.out.println(arac);
        }
    }

    public boolean idKontrol(String id){
        if(araclar.containsKey(id)){
            System.out.println("Araç zaten mevcut");
            return true;
        }
        return false;
    }
}