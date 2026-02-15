public class ProScooter extends Arac implements ISarjEdilebilir{
    private int acilisUcreti = 10;
    private int dkBasiUcret = 4;
    private int maxHiz = 50;

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
