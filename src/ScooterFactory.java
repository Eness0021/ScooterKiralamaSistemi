import java.util.Map;

public class ScooterFactory {

    private static final Map<Integer, String> TIP_SOZLUGU = Map.of(
            1, "Standart",
            2, "Pro"
    );

    public static String getTipAdi(int secim){
        return TIP_SOZLUGU.get(secim);
    }


    public static Arac scooterUret(String tip,String id, int sarj, String konum, AracDurumu durum){
        return switch (tip.toLowerCase()){
            case "pro" -> new ProScooter(id, sarj, konum, durum);
            case "standart" -> new StandartScooter(id, sarj, konum, durum);
            default -> {
                System.err.println("Uyarı: Bilinmeyen scooter tipi '" + tip + "' girildi. Bu araç sisteme eklenmedi");
                yield null;
            }
        };


    }
}
