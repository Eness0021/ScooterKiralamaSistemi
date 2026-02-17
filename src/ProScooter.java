public class ProScooter extends Arac implements ISarjEdilebilir{
    private final int acilisUcreti = 10;
    private final int dkBasiUcret = 4;
    private final int maxHiz = 50;

    public ProScooter(String aracId, int sarjYuzdesi, String konum, boolean kiradaMi) {
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
        return "ProScooter " +
                "aracId = '" + aracId + '\'' +
                ", sarjYuzdesi = " + sarjYuzdesi +
                ", konum = '" + konum + '\'';
    }
}
