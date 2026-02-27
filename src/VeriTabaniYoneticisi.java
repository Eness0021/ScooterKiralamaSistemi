import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class VeriTabaniYoneticisi implements IVeriKaynagi {

    private static final String URL = "jdbc:sqlite:scooters.db";
    private static Connection tekilBaglanti = null;

    // Veritabanı nesnesi dışarıdan üretilemez

    public static Connection getBaglanti() {
        try {
            if (tekilBaglanti == null || tekilBaglanti.isClosed()) {
                tekilBaglanti = DriverManager.getConnection(URL);
                System.out.println("Yeni Veritababnı bağlantısı açıldı");
            }
        } catch (SQLException e) {
            System.err.println("Bağlantı hatası: " + e.getMessage());
        }

        // bağlantı varsa tekrardan oluşmayacak olanı geri döndürecek
        return tekilBaglanti;
    }

    @Override
    public void tabloOlustur() {
        String sql = """
                CREATE TABLE IF NOT EXISTS araclar (
                arac_id TEXT PRIMARY KEY,
                tip TEXT NOT NULL,
                sarj_yuzdesi INTEGER NOT NULL,
                konum TEXT NOT NULL,
                durum TEXT NOT NULL
                );""";

        try (Statement statement = getBaglanti().createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            System.err.println("Tablo oluşturma hatası : " + e.getMessage());
        }
    }

    @Override
    public Map<String, Arac> verileriYukle() {
        Map<String, Arac> dbAraclar = new HashMap<>();
        String sql = "SELECT * FROM araclar";

        try (Statement stmt = getBaglanti().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("arac_id");
                String tip = rs.getString("tip");
                int sarj = rs.getInt("sarj_yuzdesi");
                String konum = rs.getString("konum");

                String durumStr = rs.getString("durum");
                AracDurumu durumEnum = AracDurumu.valueOf(durumStr);

                Arac yeniArac = ScooterFactory.scooterUret(tip, id, sarj, konum, durumEnum);

                if (yeniArac != null) dbAraclar.put(yeniArac.getAracId(), yeniArac);


            }
            System.out.println("Veritabanı kayıtları başarıyla okundu!");

        } catch (SQLException e) {
            System.err.println("Database yüklenirken hata oluştur: " + e.getMessage());
        }


        return dbAraclar;
    }


    @Override
    public void durumGuncelle(String id, AracDurumu yeniDurum) {
        String sql = "UPDATE araclar SET durum = ? WHERE arac_id = ?";

        try (PreparedStatement pstmt = getBaglanti().prepareStatement(sql)) {

            pstmt.setString(1, yeniDurum.name());
            pstmt.setString(2, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Güncelleme hatası: " + e.getMessage());
        }
    }


    @Override
    public void aracKaydet(Arac arac) {
        // Listeleri falan sildik. Sadece SQL ve PreparedStatement var.
        String sql = "INSERT INTO araclar(arac_id, tip, sarj_yuzdesi, konum, durum) VALUES(?,?,?,?,?)";

        // DİKKAT: VeriTabaniYoneticisi.getBaglanti() yerine sadece getBaglanti() yazman yeterli çünkü aynı sınıfın içindesin.
        try (PreparedStatement pstmt = getBaglanti().prepareStatement(sql)) {

            pstmt.setString(1, arac.getAracId());
            pstmt.setString(2, arac.getAracTipi());
            pstmt.setInt(3, arac.getSarjYuzdesi()); // Encapsulation için doğrudan değişkene değil getter'a eriştik
            pstmt.setString(4, arac.getKonum());
            pstmt.setString(5, arac.getDurum().name());

            pstmt.executeUpdate();
            System.out.println("Araç veritabanına kaydedildi");

        } catch (SQLException e) {
            System.err.println("Araç eklenirken veritabanı hatası : " + e.getMessage());
        }
    }
}



    /*
    public void verileriKaydet() {
        JsonArray jsonList = new JsonArray();

        for (Arac a : araclar){
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("model", (a instanceof ProScooter)? "Pro" : "Standart");
            jsonObject.addProperty("id", a.getAracId());
            jsonObject.addProperty("sarj", a.getSarjYuzdesi());
            jsonObject.addProperty("konum", a.getKonum());
            jsonObject.addProperty("kiradaMi", a.KiradaMi());


            jsonList.add(jsonObject);
        }

        try (FileWriter fileWriter = new FileWriter("scooters.json")){
            fileWriter.write(jsonList.toString());
            System.out.println("veriler başarıyla json dosyasına kaydedildi");
        }
        catch (IOException e){
            System.err.println("Kaydetme hatası : " + e.getMessage());
        }
    }
    */


    /*
    public void verileriYukle(){
        File file = new File("scooters.json");
        if(!file.exists()) return;

        try (FileReader reader  = new FileReader(file)){
            JsonArray jsonList = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement eleman : jsonList){
                JsonObject aracJson = eleman.getAsJsonObject();

                String tip = aracJson.get("model").getAsString();
                String id = aracJson.get("id").getAsString();
                int sarj = aracJson.get("sarj").getAsInt();
                String konum = aracJson.get("konum").getAsString();
                boolean kiradaMi = aracJson.get("kiradaMi").getAsBoolean();

                if(tip.equals("Pro")){
                    araclar.add(new ProScooter(id,sarj,konum,kiradaMi));
                }
                else {
                    araclar.add(new StandartScooter(id,sarj,konum,kiradaMi));
                }
            }
        }
        catch (IOException e){
            System.out.println("Yükleme hatası : " + e.getMessage());

        }
    }
    */

