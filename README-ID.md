<p align="center">
  <img src="assets/banner.svg" alt="NT-Code Popup Blocker" width="100%" />
</p>

<p align="center">
  <a href="https://github.com/mchasby/LG-V50-NTCode-Popup-Blocker/actions/workflows/build-apk.yml"><img src="https://github.com/mchasby/LG-V50-NTCode-Popup-Blocker/actions/workflows/build-apk.yml/badge.svg?branch=main" alt="Status build"></a>
  <a href="https://github.com/mchasby/LG-V50-NTCode-Popup-Blocker/releases/latest"><img src="https://img.shields.io/github/v/release/mchasby/LG-V50-NTCode-Popup-Blocker?display_name=tag&label=rilis%20terbaru" alt="Rilis terbaru"></a>
  <a href="https://github.com/mchasby/LG-V50-NTCode-Popup-Blocker/releases"><img src="https://img.shields.io/github/downloads/mchasby/LG-V50-NTCode-Popup-Blocker/total?label=unduhan" alt="Jumlah unduhan"></a>
</p>

# NT-Code Popup Blocker

Modul LSPosed kecil untuk LG V50 ThinQ yang menyembunyikan satu dialog peringatan NT-Code tertentu di proses Phone Services. Modul memeriksa judul dan isi pesan sebelum mencoba memblokir dialog, agar dialog lain tidak ikut ditargetkan.

> **Penting:** modul ini hanya menyembunyikan peringatan yang cocok. Modul tidak memperbaiki NT-Code, konfigurasi operator, firmware, modem, atau masalah pencocokan carrier.

## Download

**[Download APK rilis terbaru](https://github.com/mchasby/LG-V50-NTCode-Popup-Blocker/releases/latest)**

Jika belum ada rilis, buka [Actions](https://github.com/mchasby/LG-V50-NTCode-Popup-Blocker/actions) dan cek artifact dari build yang berhasil.

## Fitur

- Menargetkan package Android `com.android.phone` saja.
- Memeriksa judul `WARNING!` serta potongan pesan `Can't find matched carrier` dan `Check NT-Code: 0`.
- Mencoba memblokir hanya `AlertDialog` yang teksnya cocok.
- GitHub Actions dapat membangun APK dan melampirkannya otomatis ke GitHub Release saat tag versi dibuat.

## Kompatibilitas

| Item | Detail |
|---|---|
| Perangkat tujuan | LG V50 ThinQ |
| Perangkat yang dilaporkan berhasil diuji | LG V50 ThinQ LM-V500VM, Android 12 |
| Root / framework | Magisk + Zygisk + LSPosed |
| Scope LSPosed yang wajib | `Call services` (`com.android.phone`) saja |
| Scope yang tidak diperlukan | `System Framework` |
| Application ID | `com.nrowa.ntcodepopup` |

Kompatibilitas dengan model LG lain, Android lain, ROM lain, atau versi LSPosed lain belum dipastikan.

## Instalasi

1. Download APK dari [Releases](https://github.com/mchasby/LG-V50-NTCode-Popup-Blocker/releases/latest).
2. Instal APK di HP.
3. Buka LSPosed Manager dan aktifkan **NT-Code Popup Blocker**.
4. Buka scope modul dan pilih **Call services** (`com.android.phone`) saja.
5. Reboot HP agar hook dimuat ke proses Phone Services.

Jangan aktifkan scope `System Framework` untuk modul ini.

## Menonaktifkan / rollback

1. Buka LSPosed Manager.
2. Nonaktifkan **NT-Code Popup Blocker**.
3. Reboot HP.
4. Setelah modul benar-benar nonaktif, APK boleh di-uninstall jika tidak diperlukan lagi.

## Build dari source

Project memakai Java 17, Android Gradle Plugin 8.6.1, Gradle 8.7, dan Android SDK Platform 35.

Build otomatis berjalan saat push ke branch `main`/`master` dan bisa dijalankan manual dari tab **Actions**. Push tag versi seperti `v1.0.0` akan menjalankan build dan menerbitkan GitHub Release berisi APK debug.

APK unduhan merupakan **debug build**, bukan APK yang ditandatangani dengan release key produksi.

## Troubleshooting

- **Peringatan masih muncul:** pastikan modul aktif, scope hanya `com.android.phone`, dan HP sudah direboot. Periksa LSPosed logs untuk entri `[NTCodePopupBlocker]`.
- **APK tidak ada di Releases:** buka run terkait di [Actions](https://github.com/mchasby/LG-V50-NTCode-Popup-Blocker/actions) dan periksa job build serta release.
- **Ingin membatalkan:** nonaktifkan modul di LSPosed lalu reboot.

## Batasan dan risiko

Ini workaround khusus perangkat. Modul mengubah perilaku dialog, bukan memperbaiki sumber peringatan NT-Code. Gunakan dengan risiko sendiri dan siapkan rencana pemulihan yang sesuai sebelum mengubah konfigurasi root atau sistem.

## Language

- [English](README.md)

## Lisensi

Belum ada file lisensi. Sampai lisensi ditambahkan, repository ini belum memberikan izin umum untuk menggunakan ulang atau mendistribusikan source code.
