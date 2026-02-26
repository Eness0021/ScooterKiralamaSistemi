public abstract class Arac implements ISarjEdilebilir{
    protected String aracId;
    protected int sarjYuzdesi;
    protected String konum;
    private AracDurumu durum;

    public Arac(String aracId, int sarjYuzdesi, String konum, AracDurumu durum) {
        this.aracId = aracId;
        this.sarjYuzdesi = sarjYuzdesi;
        this.konum = konum;
        this.durum = durum;
    }

    public abstract double ucretHesapla(int dakika);

    public abstract String getAracTipi();


    public String getAracId() {
        return aracId;
    }

    public void setAracId(String aracId) {
        this.aracId = aracId;
    }

    public int getSarjYuzdesi() {
        return sarjYuzdesi;
    }

    public void setSarjYuzdesi(int sarjYuzdesi) {
        this.sarjYuzdesi = sarjYuzdesi;
    }

    public String getKonum() {
        return konum;
    }

    public void setKonum(String konum) {
        this.konum = konum;
    }

    public AracDurumu getDurum() {
        return durum;
    }

    public void setDurum(AracDurumu durum) {
        this.durum = durum;
    }
}
