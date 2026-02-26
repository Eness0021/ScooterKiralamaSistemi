import java.util.List;

public interface IVeriKaynagi {
    void tabloOlustur();
    List<Arac> verileriYukle();
    void aracKaydet(Arac arac);
    void durumGuncelle(String id, AracDurumu yeniDurum);

}

