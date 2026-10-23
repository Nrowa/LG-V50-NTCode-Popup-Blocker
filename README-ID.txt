NT-Code Popup Blocker — SOURCE PROJECT
Author: Nrowa
Package: com.nrowa.ntcodepopup
Target: LSPosed legacy API; Android 8+; source logic based on user-provided UI XML. Not yet compiled or tested on a device.

PENTING
- Arsip ini adalah SOURCE PROJECT, bukan APK siap-install.
- Workflow GitHub Actions disertakan di .github/workflows/build-apk.yml; bisa membangun debug APK setelah source di-upload ke repository GitHub pada branch main atau master.
- Modul menyasar package com.android.phone dan mencoba membatalkan pemanggilan AlertDialog.show() bila JUDUL mengandung "WARNING!" DAN PESAN mengandung kedua string:
  "Can't find matched carrier"
  "Check NT-Code: 0"
- Dialog lain tidak ditargetkan oleh logika pencocokan. Namun, karena belum diuji di perangkat, jangan anggap perilakunya pasti berhasil.
- Ini hanya menyembunyikan popup; tidak memperbaiki NT-Code, carrier, FTM, firmware, maupun modem.
- Tidak ada jaminan kompatibilitas atau bebas crash. Uji dengan risiko sendiri; jalur pemulihan pengguna mengandalkan QFIL.

BUILD OTOMATIS
Lihat UPLOAD-KE-GITHUB.txt. Upload ISI folder project (termasuk folder .github) ke root repository. Build akan berjalan pada push ke main/master, atau bisa dijalankan manual di tab Actions. APK debug akan tersedia sebagai Artifact jika build sukses.

BUILD MANUAL
1. Buka folder ini sebagai project di Android Studio.
2. Siapkan Android SDK Platform 35, JDK 17, dan koneksi internet untuk mengunduh Gradle/dependency.
3. Jalankan task :app:assembleDebug.
4. APK biasanya ada di app/build/outputs/apk/debug/app-debug.apk.

INSTALL & SCOPE
1. Install APK hasil build di Android.
2. LSPosed Manager > Modules > NT-Code Popup Blocker > enable.
3. Pilih scope HANYA Call services / com.android.phone.
4. Reboot agar hook dimuat ke proses Phone.
5. Periksa LSPosed Logs. Entri diagnostic yang diharapkan:
   [NTCodePopupBlocker] Hook installed for AlertDialog.show().
   Jika pesan cocok:
   [NTCodePopupBlocker] Suppressed matching LG NT-Code dialog.

ROLLBACK
Matikan modul ini di LSPosed dan reboot. Jangan mengaktifkan scope System Framework; modul ini tidak membutuhkannya.
