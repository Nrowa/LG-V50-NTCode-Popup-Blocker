package com.nrowa.ntcodepopup;

import android.app.AlertDialog;
import android.text.TextUtils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Narrow-scope Xposed hook: suppress only the known LG NT-Code warning
 * observed in package com.android.phone. Other dialogs are left untouched.
 */
public final class NtCodeHook implements IXposedHookLoadPackage {
    private static final String TARGET_PACKAGE = "com.android.phone";
    private static final String TITLE_FRAGMENT = "WARNING!";
    private static final String MESSAGE_FRAGMENT_1 = "Can't find matched carrier";
    private static final String MESSAGE_FRAGMENT_2 = "Check NT-Code: 0";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (!TARGET_PACKAGE.equals(lpparam.packageName)) {
            return;
        }

        try {
            XposedBridge.log("[NTCodePopupBlocker] Loading in " + lpparam.packageName
                    + " process=" + lpparam.processName);

            XposedBridge.hookAllMethods(AlertDialog.class, "show", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    try {
                        if (!(param.thisObject instanceof AlertDialog)) {
                            return;
                        }

                        AlertDialog dialog = (AlertDialog) param.thisObject;
                        CharSequence titleValue = dialog.getTitle();
                        CharSequence messageValue = dialog.getMessage();
                        String title = titleValue == null ? "" : titleValue.toString();
                        String message = messageValue == null ? "" : messageValue.toString();

                        boolean matches = title.contains(TITLE_FRAGMENT)
                                && message.contains(MESSAGE_FRAGMENT_1)
                                && message.contains(MESSAGE_FRAGMENT_2);

                        if (matches) {
                            XposedBridge.log("[NTCodePopupBlocker] Suppressed matching LG NT-Code dialog.");
                            // Skip AlertDialog.show() for this exact warning only.
                            param.setResult(null);
                        }
                    } catch (Throwable t) {
                        XposedBridge.log("[NTCodePopupBlocker] Dialog inspection failed: " + t);
                    }
                }
            });
            XposedBridge.log("[NTCodePopupBlocker] Hook installed for AlertDialog.show().");
        } catch (Throwable t) {
            XposedBridge.log("[NTCodePopupBlocker] Failed to install hook: " + t);
        }
    }
}
