# 🛴 Scooter Kiralama Sistemi (Scooter Rental System)

Bu proje, nesne yönelimli programlama (OOP) temelleri üzerine inşa edilmiş, **SOLID prensiplerine** ve modern yazılım mimarisi standartlarına uygun olarak geliştirilmiş Java tabanlı bir araç kiralama simülasyonudur. Sistem, araç kiralama, müsaitlik sorgulama ve SQLite entegrasyonu ile veri kalıcılığı sağlama gibi işlemleri yönetir.

## 🚀 Projenin Amacı ve Mimari Yaklaşım
Bu projenin temel odak noktası sadece çalışan bir sistem yapmak değil; aynı zamanda değişime açık, bakımı kolay ve esnek bir sistem (Clean Architecture) tasarlamaktır. Proje genelinde sıkı bağlılıklar (tight coupling) engellenmiş ve veri kaynakları arayüzler arkasına gizlenmiştir.

### 🏗️ Uygulanan Tasarım Kalıpları (Design Patterns)
* **Factory Pattern (Fabrika Kalıbı):** Nesne üretim mantığı `ScooterFactory` sınıfına devredilmiştir. Yeni bir araç türü eklendiğinde (örn: EcoScooter), ana kodda hiçbir `if-else` değişikliği yapmadan sadece fabrikaya yeni türün tanıtılması yeterlidir.
* **Singleton Pattern:** Veritabanı bağlantısı (Connection nesnesi) `VeriTabaniYoneticisi` içinde Singleton olarak tasarlanmıştır. Bu sayede her sorguda veritabanına yeniden bağlanma maliyeti (performans kaybı) önlenmiş, bellek dostu bir yapı kurulmuştur.
* **Dependency Injection (Bağımlılık Enjeksiyonu):** İş mantığı (`KiralamaSistemi`) doğrudan SQLite'a bağlı değildir. `IVeriKaynagi` arayüzü (interface) sayesinde veritabanı bağımlılığı dışarıdan enjekte edilir. İleride JSON veya MongoDB gibi farklı veri kaynaklarına geçiş yapmak tek satırlık bir işleme indirgenmiştir.

### 🛡️ SOLID Prensipleri
* **S - Single Responsibility:** Sınıfların görevleri kesin çizgilerle ayrılmıştır. `KullaniciArayuzu` sadece menü gösterir, `KiralamaSistemi` sadece iş mantığını yönetir, `VeriTabaniYoneticisi` sadece SQL işlemleri yapar.
* **O - Open/Closed:** Sisteme yeni araç türleri eklenmesi (Gelişime açık), mevcut kodların (özellikle if-else veya instanceof bloklarının) değiştirilmesini gerektirmez (Değişime kapalı).
* **L - Liskov Substitution:** Alt sınıflar (`ProScooter`, `StandartScooter`), üst sınıflarının (`Arac`) yerine hiçbir davranışı bozmadan polimorfik olarak kullanılabilir.
* **I - Interface Segregation:** `ISarjEdilebilir` ve `IVeriKaynagi` arayüzleri, sadece gereken metodları barındıracak şekilde özelleştirilmiş, sınıflar gereksiz metodları ezmeye (override) zorlanmamıştır.
* **D - Dependency Inversion:** Yüksek seviyeli kiralama sınıfı, düşük seviyeli veritabanı sınıfına değil; her ikisi de soyut arayüzlere (`IVeriKaynagi`) bağımlı hale getirilmiştir.

## 🛠️ Kullanılan Teknolojiler
* **Dil:** Java (Modern Switch Expressions dahil)
* **Veritabanı:** SQLite & JDBC Driver
* **Kavramlar:** OOP (Kalıtım, Polimorfizm, Kapsülleme, Soyutlama), Custom Exceptions (`YetersizSarjException`)

## 📋 Özellikler
* Standart ve Pro model scooter ekleme (Farklı fiyatlandırma politikaları).
* Araç müsaitlik ve şarj durumu (%20 sınırı) kontrolleri.
* Özel Exception fırlatma ile hata yönetimi.
* Tüm verilerin SQLite veritabanında kalıcı olarak saklanması.

## ⚙️ Kurulum ve Çalıştırma

1. Projeyi bilgisayarınıza klonlayın:
   ```bash
   git clone [https://github.com/Eness0021/Scooter-Kiralama-Sistemi.git](https://github.com/Eness0021/Scooter-Kiralama-Sistemi.git)
