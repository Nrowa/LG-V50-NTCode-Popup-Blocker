# Changelog

Notable changes to NT-Code Popup Blocker are recorded here.

## [1.0.0] — Initial release

- Add an LSPosed hook targeting the matching LG NT-Code warning in `com.android.phone`.
- Match the dialog title and two message fragments before suppressing it.
- Add GitHub Actions build automation and tagged-release APK publishing.
- User-reported successful test on LG V50 ThinQ LM-V500VM running Android 12.

### Limitations

- This suppresses the warning dialog; it does not repair NT-Code or carrier configuration.
- Compatibility outside the reported test environment is unverified.
- The downloadable package is a debug APK, not a production-signed build.
