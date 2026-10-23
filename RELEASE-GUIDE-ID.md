# Panduan rilis GitHub (Bahasa Indonesia)

Workflow `build-apk.yml` sudah disiapkan agar tag versi dengan awalan `v` memicu build dan membuat GitHub Release otomatis.

## Cara menerbitkan rilis pertama

1. Pastikan perubahan terakhir sudah di-commit ke branch `main` dan build biasa di tab **Actions** berhasil hijau.
2. Buka tab **Code** → klik dropdown branch/tag di sebelah kiri daftar file → pilih **Tags** bila tersedia, atau buka halaman **Releases** di sidebar kanan.
3. Cara paling mudah: buka **Releases** → **Draft a new release**.
4. Pada **Choose a tag**, ketik `v1.0.0`, lalu pilih **Create new tag: v1.0.0 on publish** (target `main`).
5. Isi judul `NT-Code Popup Blocker v1.0.0`.
6. Kamu boleh menaruh isi `RELEASE_NOTES_v1.0.0.md` di kolom deskripsi. Jangan upload APK secara manual bila memakai workflow otomatis.
7. Publikasikan release. Pembuatan tag `v1.0.0` akan memicu workflow. Setelah job build sukses, job `Publish GitHub Release` akan melampirkan APK debug secara otomatis.
8. Buka **Actions** dan tunggu kedua job hijau. Lalu cek **Releases** untuk file `NTCode-Popup-Blocker-v1.0.0-debug.apk`.

Jika workflow belum diperbarui di repository, edit file `.github/workflows/build-apk.yml` di GitHub dan ganti isinya dengan versi di paket ini sebelum membuat tag.

## Memperbarui versi nanti

- Bug fix kecil: buat tag berikutnya, misalnya `v1.0.1`.
- Fitur tambahan yang kompatibel: `v1.1.0`.
- Perubahan besar yang tidak kompatibel: `v2.0.0`.

Pastikan `app/build.gradle` punya versi yang sesuai sebelum menerbitkan versi berikutnya. Saat ini `versionName` masih `1.0` dan `versionCode` masih `1`, jadi ubah keduanya saat menyiapkan pembaruan aplikasi berikutnya.
