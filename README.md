# 8 Mart Dünya Kadınlar Günü Uygulaması

![En İyi Mobil Oyunlar Youtube Kapağı](https://github.com/user-attachments/assets/64e79934-6413-410f-a991-8496d81dbd9b)

Bu mobil uygulama, Dünya Kadınlar Günü kapsamında Marie Claire dergisinin "Dünyayı Değiştiren Tarihteki 65 Ünlü Kadın" listesini tanıtmak amacıyla geliştirilmiştir.

## Özellikler

- Firebase Realtime Database kullanarak kadın liderler hakkında bilgi depolama
- Jetpack Compose ile modern UI tasarımı
- Koyu tema ile şık bir arayüz
- Kişi detaylarını görmek için alt sayfa (ModalBottomSheet)
- Wikipedia bağlantısı

## Teknik Detaylar

### Kullanılan Teknolojiler

- **Jetpack Compose**: Modern Android UI toolkit
- **Firebase Realtime Database**: Veri saklama ve senkronizasyon
- **MVVM Mimarisi**: ViewModel ve Repository pattern
- **Coil**: Görsel yükleme kütüphanesi
- **Accompanist Pager**: Sayfalama efektleri

### Veri Modeli

```kotlin
data class WomanItemModel(
    var birth: Int = 0,
    var death: Int = 0,
    var name: String = "",
    var picUrl: String = "",
    var profession: String = "",
    var notable: String = ""
): Serializable
```

### Repository

- Firebase veritabanından kadın liderler listesini yükler
- LiveData kullanarak veri akışı sağlar

### UI Bileşenleri

- `MainScreen`: Ana ekran yapısı
- `MainContent`: İçerik düzeni ve veri yükleme
- `MovieList`: Kadın liderlerin yatay kaydırmalı listesi
- `BottomSheetContent`: Seçilen kadınla ilgili detay sayfası

## Kurulum

1. Projeyi klonlayın
2. Firebase projenizi oluşturun ve google-services.json dosyasını ekleyin
3. Gradle bağımlılıklarını senkronize edin
4. Uygulamayı derleyin ve çalıştırın

