import java.sql.*;


public class VeriTabaniYoneticisi {
    private static final String URL = "jdbc:sqlite:scooters.db";

    public static Connection baglan(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        }
        catch (SQLException e){
            System.err.println("Bağlantı hatası : " + e.getMessage());
        }
        return connection;
    }


    public static void tabloOLustur(){
        String sql = "CREATE TABLE IF NOT EXISTS araclar (\n"
                + "arac_id TEXT PRIMARY KEY,\n"
                + "tip TEXT NOT NULL,\n"
                + "sarj_yuzdesi INTEGER NOT NULL,\n"
                + "konum TEXT NOT NULL,\n"
                +  "kirada_mi BOOLEAN NOT NULL\n"
                + ");";

        try (Connection connection = baglan();
            Statement statement = connection.createStatement()){

            statement.execute(sql);

        }
        catch (SQLException e){
            System.err.println("Tablo oluşturma hatası : " + e.getMessage());
        }
    }
}



