public class StandartScooter extends Arac implements ISarjEdilebilir{
    private final int maxHiz = 30;
    public StandartScooter(String aracId, int sarjYuzdesi, String konum, AracDurumu durum) {
        super(aracId, sarjYuzdesi, konum, durum);
    }


    @Override
    public double ucretHesapla(int dakika){
        int dkBasiUcret = 2;
        int acilisUcreti = 5;
        return ((dkBasiUcret *dakika) + acilisUcreti);
    }


    @Override
    public void sarjEt() {
        super.sarjYuzdesi = 100;
        System.out.println("Araç şarj edildi");
    }


    @Override
    public String getAracTipi(){
        return "Standart";
    }




    @Override
    public String toString() {
        return "StandartScooter " +
                "aracId = '" + aracId + '\'' +
                ", sarjYuzdesi = " + sarjYuzdesi +
                ", konum = '" + konum + '\'' +
                ", durum = " + super.getDurum();

    }
}
