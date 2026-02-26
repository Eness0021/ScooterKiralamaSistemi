import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class VeriTabaniYoneticisi implements IVeriKaynagi{

    private static final String URL = "jdbc:sqlite:scooters.db";
    private static Connection tekilBaglanti = null;

    // Veritabanı nesnesi dışarıdan üretilemez

    public static Connection getBaglanti(){
        try {
            if(tekilBaglanti == null || tekilBaglanti.isClosed()){
                tekilBaglanti = DriverManager.getConnection(URL);
                System.out.println("Yeni Veritababnı bağlantısı açıldı");
            }
        } catch (SQLException e){
            System.err.println("Bağlantı hatası: " + e.getMessage());
        }

        // bağlantı varsa tekrardan oluşmayacak olanı geri döndürecek
        return tekilBaglanti;
    }

    @Override
    public void tabloOlustur(){
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

        }
        catch (SQLException e){
            System.err.println("Tablo oluşturma hatası : " + e.getMessage());
        }
    }

    @Override
    public List<Arac> verileriYukle(){
        List<Arac> dbAraclar = new ArrayList<>();
        String sql = "SELECT * FROM araclar";

        try (Statement stmt = getBaglanti().createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                String id = rs.getString("arac_id");
                String tip = rs.getString("tip");
                int sarj = rs.getInt("sarj_yuzdesi");
                String konum = rs.getString("konum");

                String durumStr = rs.getString("durum");
                AracDurumu durumEnum = AracDurumu.valueOf(durumStr);

                Arac yeniArac = ScooterFactory.scooterUret(tip,id,sarj,konum,durumEnum);

                if(yeniArac!= null) dbAraclar.add(yeniArac);


            }
            System.out.println("Veritabanı kayıtları başarıyla okundu!");

        }
        catch (SQLException e){
            System.err.println("Database yüklenirken hata oluştur: " + e.getMessage());
        }



        return dbAraclar;
    }


    @Override
    public void durumGuncelle(String id,AracDurumu yeniDurum){
        String sql = "UPDATE araclar SET durum = ? WHERE arac_id = ?";

        try (PreparedStatement pstmt = getBaglanti().prepareStatement(sql)){

            pstmt.setString(1, yeniDurum.name());
            pstmt.setString(2,id);

            pstmt.executeUpdate();

        }
        catch (SQLException e){
            System.err.println("Güncelleme hatası: " + e.getMessage());
        }
    }


    @Override
    public void aracKaydet(Arac arac){
        // Listeleri falan sildik. Sadece SQL ve PreparedStatement var.
        String sql = "INSERT INTO araclar(arac_id, tip, sarj_yuzdesi, konum, durum) VALUES(?,?,?,?,?)";

        // DİKKAT: VeriTabaniYoneticisi.getBaglanti() yerine sadece getBaglanti() yazman yeterli çünkü aynı sınıfın içindesin.
        try (PreparedStatement pstmt = getBaglanti().prepareStatement(sql)){

            pstmt.setString(1, arac.getAracId());
            pstmt.setString(2, arac.getAracTipi());
            pstmt.setInt(3, arac.getSarjYuzdesi()); // Encapsulation için doğrudan değişkene değil getter'a eriştik
            pstmt.setString(4, arac.getKonum());
            pstmt.setString(5, arac.getDurum().name());

            pstmt.executeUpdate();
            System.out.println("Araç veritabanına kaydedildi");

        }
        catch (SQLException e){
            System.err.println("Araç eklenirken veritabanı hatası : " + e.getMessage());
        }
    }


}



