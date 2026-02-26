public class ProScooter extends Arac implements ISarjEdilebilir{
    private final int maxHiz = 50;

    public ProScooter(String aracId, int sarjYuzdesi, String konum, AracDurumu durum) {
        super(aracId, sarjYuzdesi, konum, durum);
    }


    @Override
    public double ucretHesapla(int dakika){
        int dkBasiUcret = 4;
        int acilisUcreti = 10;
        return ((dkBasiUcret *dakika) + acilisUcreti);
    }



    @Override
    public void sarjEt() {
        super.sarjYuzdesi = 100;
        System.out.println("Araç şarj edildi");
    }

    @Override
    public String getAracTipi(){
        return "Pro";
    }




    @Override
    public String toString() {
        return "ProScooter " +
                "aracId = '" + aracId + '\'' +
                ", sarjYuzdesi = " + sarjYuzdesi +
                ", konum = '" + konum + '\'' +
                ", durum = " + super.getDurum();
    }
}
