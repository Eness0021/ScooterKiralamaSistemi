import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class KiralamaSistemi{
    List<Arac> araclar = new ArrayList<>();


    public void aracEkle(Arac arac){
        araclar.add(arac);
    }

    public void aracKirala(String id, int sure) throws YetersizSarjException{
        for (Arac a : araclar){
            if (a.aracId.equalsIgnoreCase(id)){
                if(a.getSarjYuzdesi() < 20){
                    throw new YetersizSarjException("Hata : Aracın şarjı çok düşük");
                }
                if(!a.KiradaMi()){
                    System.out.println("Araç kiralandı");
                    System.out.print("Ücret : " + a.ucretHesapla(sure));
                    System.out.println();
                    a.setKiradaMi(true);
                }
                else {
                    System.out.println("Hata : Araç zaten kirada");
                }
                return;
            }
        }
        System.out.println("Hata : Araç bulunamadı");
    }

    public void tumAraclariListele(){
        for (Arac arac : araclar){
            System.out.println(arac);
        }
    }

    public boolean idKontrol(String id){
        for (Arac arac : araclar){
            if(id.equalsIgnoreCase(arac.aracId)){
                System.out.println("Araç zaten mevcut");
                return true;
            }
        }
        return false;
    }

    public boolean musaitlikSorgulama(String id){
        for (Arac arac : araclar){
            if (arac.aracId.equalsIgnoreCase(id)){
                if (arac.KiradaMi() || arac.getSarjYuzdesi() < 20){
                    System.out.println("Araç şuan müsait değil");
                    return false;
                }
                else{
                    System.out.println("Araç şuan müsait");
                    return true;
                }
            }
        }
        System.out.println("girilen id ye sahip araç bulunamadı!");
        return false;
    }




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




}
