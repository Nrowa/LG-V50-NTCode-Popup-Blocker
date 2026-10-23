# NT-Code Popup Blocker v1.1.0

## What's included

- Custom application launcher and adaptive icons.
- Refined LSPosed hook targeting `android.app.Dialog.show()`.
- Narrow matching for the LG NT-Code warning title and message in `com.android.phone`.

## Compatibility

- User-reported successful test: LG V50 ThinQ LM-V500VM running Android 12.
- Intended setup: Magisk + Zygisk + LSPosed.
- Required LSPosed scope: `Call services` (`com.android.phone`) only.
- Other devices and software combinations are unverified.

## Install

1. Download `NTCode-Popup-Blocker-v1.1.0-debug.apk` from the release assets.
2. Install the APK.
3. Enable **NT-Code Popup Blocker** in LSPosed.
4. Select **Call services** (`com.android.phone`) only.
5. Reboot.

## Important notes

- This module hides the matching warning dialog; it does not fix NT-Code, carrier configuration, firmware, or modem behavior.
- This is a debug build, not a production-signed release.
- Keep a rollback plan and disable the module in LSPosed before removing it if needed.
