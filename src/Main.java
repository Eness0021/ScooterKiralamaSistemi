public class Main {
    public static void main(String[] args) {

        IVeriKaynagi veriTabani = new VeriTabaniYoneticisi();
        veriTabani.tabloOlustur();

        KiralamaSistemi sistem = new KiralamaSistemi(veriTabani);

        KullaniciArayuzu arayuz = new KullaniciArayuzu(sistem);
        arayuz.baslat();
    }
}