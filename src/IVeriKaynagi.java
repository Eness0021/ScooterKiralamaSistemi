import java.util.Map;

public interface IVeriKaynagi {
    void tabloOlustur();
    Map<String, Arac> verileriYukle();
    void aracKaydet(Arac arac);
    void durumGuncelle(String id, AracDurumu yeniDurum);

}

