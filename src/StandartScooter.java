public class StandartScooter extends Arac implements ISarjEdilebilir{
    private int acilisUcreti = 5;
    private int dkBasiUcret = 2;

    public StandartScooter(String aracId, int sarjYuzdesi, String konum, boolean kiradaMi) {
        super(aracId, sarjYuzdesi, konum, kiradaMi);
    }


    @Override
    public double ucretHesapla(int dakika){
        return ((dkBasiUcret*dakika) + acilisUcreti);
    }


    @Override
    public void sarjEt() {
        super.sarjYuzdesi = 100;
        System.out.println("Araç şarj edildi");
    }


    @Override
    public String toString() {
        return "StandartScooter " +
                "aracId = '" + aracId + '\'' +
                ", sarjYuzdesi = " + sarjYuzdesi +
                ", konum = '" + konum + '\'';
    }
}
