# Changelog

Notable changes to NT-Code Popup Blocker are recorded here. Version tags follow `MAJOR.MINOR.PATCH` where practical.

## [1.1.0] — Custom icon and hook refinement

- Add custom launcher and adaptive icon resources.
- Refine the dialog hook to target `android.app.Dialog.show()`.
- Keep matching limited to the LG NT-Code warning title/message in `com.android.phone`.
- Document automatic APK publishing through GitHub Actions.
- User-reported successful test on LG V50 ThinQ LM-V500VM running Android 12.

### Limitations

- The module suppresses a matching warning dialog; it does not repair NT-Code or carrier configuration.
- Compatibility outside the reported test environment is unverified.
- The downloadable package is a debug APK, not a production-signed build.

## [1.0.0] — Initial release

- Add an LSPosed hook targeting the matching LG NT-Code warning in `com.android.phone`.
- Match the dialog title and message fragments before suppression.
- Add GitHub Actions build automation and tagged-release APK publishing.
