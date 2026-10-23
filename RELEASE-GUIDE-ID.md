# Panduan Rilis GitHub (Bahasa Indonesia)

Panduan ini berlaku untuk rilis berikutnya setelah `v1.1.0`. Workflow `.github/workflows/build-apk.yml` akan membangun APK saat tag `v*` didorong dan kemudian membuat atau memperbarui GitHub Release untuk tag itu.

## Sebelum merilis

1. Pastikan perubahan kode sudah di-commit dan di-push ke branch `main`.
2. Buka tab **Actions** dan pastikan build biasa terbaru berstatus hijau.
3. Uji APK pada konfigurasi yang dituju bila memungkinkan. Rilis saat ini adalah **debug APK**, bukan APK produksi yang ditandatangani dengan release key.
4. Jangan mengubah atau memakai ulang tag yang sudah diterbitkan. Tag `v1.1.0` sudah digunakan; versi berikutnya harus memakai tag baru.

## Menyiapkan nomor versi

Untuk rilis aplikasi berikutnya setelah `v1.1.0`, contoh versi patch berikutnya adalah `v1.1.1`. Sebelum membuat tag, ubah `app/build.gradle` agar nomor versi aplikasi cocok:

```gradle
versionCode 3
versionName '1.1.1'
```

`versionCode` harus selalu naik dibandingkan rilis aplikasi sebelumnya. Untuk perubahan dokumentasi saja, tidak perlu menaikkan versi aplikasi atau menerbitkan APK baru.

Pedoman versi:

- Perbaikan kecil yang kompatibel: naikkan patch, misalnya `v1.1.1`.
- Fitur baru yang kompatibel: naikkan minor, misalnya `v1.2.0`.
- Perubahan besar yang tidak kompatibel: naikkan major, misalnya `v2.0.0`.

Commit dan push perubahan versi ke `main`, lalu pastikan build biasa kembali hijau sebelum merilis.

## Membuat GitHub Release

1. Buka repository → **Releases** → **Draft a new release**.
2. Pada **Choose a tag**, ketik tag baru, misalnya `v1.1.1`, lalu pilih **Create new tag: v1.1.1 on publish**.
3. Pastikan target adalah branch `main`.
4. Isi judul, misalnya `NT-Code Popup Blocker v1.1.1`.
5. Tulis ringkasan perubahan pada release notes. Cantumkan kompatibilitas, perubahan penting, dan keterbatasan yang diketahui.
6. Karena APK saat ini adalah debug build, centang **Set as a pre-release** sampai tersedia proses build dan penandatanganan produksi yang sesuai.
7. Klik **Publish release**. Tag baru akan memicu workflow secara otomatis. Jangan mengunggah APK secara manual; workflow yang akan melampirkan APK hasil build.

## Memeriksa hasil rilis

1. Buka tab **Actions** dan pilih run yang dipicu tag baru.
2. Pastikan job **Build debug APK** dan **Publish GitHub Release** sama-sama hijau.
3. Buka **Releases** → rilis yang baru dibuat.
4. Pastikan bagian **Assets** berisi file seperti `NTCode-Popup-Blocker-v1.1.1-debug.apk`.
5. Bila job gagal, baca log dan perbaiki penyebabnya sebelum membuat rilis versi berikutnya. Jangan menggeser tag yang sudah diterbitkan; gunakan nomor versi baru untuk koreksi berikutnya.

## Catatan penting

- Modul ini hanya mencoba menyembunyikan dialog LG NT-Code yang judul dan pesannya cocok. Modul tidak memperbaiki NT-Code atau konfigurasi carrier.
- Scope LSPosed yang diperlukan adalah `Call services` (`com.android.phone`) saja; jangan aktifkan `System Framework` untuk modul ini.
- Workflow saat ini memasang Gradle 8.7 di runner GitHub Actions, sehingga Gradle Wrapper belum diperlukan untuk CI ini. `.gitignore` tersedia untuk mencegah file lokal dan hasil build masuk ke repository.
- APK debug dapat memerlukan uninstall versi sebelumnya jika tanda tangan APK berbeda. Jangan menghapus atau menonaktifkan modul yang sedang berfungsi tanpa menyiapkan langkah rollback.
