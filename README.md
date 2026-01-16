# Smart City System

A simple **Spring Boot backend** for a Smart City application.  
The system receives IoT sensor data, applies basic rules, and generates alerts.  
It also includes a modular digital banking component for demonstration purposes.

---

## Proje Hakkında

Bu proje, bir **Akıllı Şehir (Smart City)** senaryosu için geliştirilmiş backend uygulamasıdır.

Sistem:
- Sensörlerden veri alır
- Belirlenen kurallara göre uyarı (alert) üretir
- Üretilen verileri veritabanında saklar
- Rol bazlı güvenlik kullanır

Proje eğitim ve portföy amaçlıdır.

---

## Kullanılan Teknolojiler

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Security (JWT)  
- SQLite  
- Docker & Docker Compose  
- Swagger (OpenAPI)

---

## Özellikler

### Sensör & Uyarı Sistemi
- Sensör verisi alma (`/sensor/ingest`)
- Kural bazlı kontrol (örnek: trafik yoğunluğu)
- Uyarı (alert) üretimi
- Observer pattern kullanımı
- Son sensör ve uyarı kayıtlarını listeleme

### Bankacılık Modülü
- Rol bazlı erişim (`RESIDENT`)
- İşlem (transaction) kaydı
- Otopark ve kripto (mock) ödeme işlemleri
- Command ve Adapter pattern örnekleri

### Güvenlik
- JWT tabanlı kimlik doğrulama
- Stateless yapı
- Endpoint bazlı yetkilendirme

---

## Çalışma Adresi

Uygulama şu adreste çalışır:

http://localhost:8080



---

## API Dokümantasyonu

Swagger arayüzü:

http://localhost:8080/swagger-ui.html



---

Örnek İstek
curl -X POST http://localhost:8080/sensor/ingest \
  -H "Content-Type: application/json" \
  -d '{"type":"traffic","value":81}'

---


## Docker ile Çalıştırma

```bash
docker compose up --build
