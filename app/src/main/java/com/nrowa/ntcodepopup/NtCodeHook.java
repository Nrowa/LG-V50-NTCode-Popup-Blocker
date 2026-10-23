package com.nrowa.ntcodepopup;

import android.app.AlertDialog;
import android.app.Dialog;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Suppress only the specific LG NT-Code warning in com.android.phone.
 * Hook Dialog.show(), where show() is declared, rather than hookAllMethods()
 * on AlertDialog (which may not declare show() itself).
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

        XposedBridge.log("[NTCodePopupBlocker] Loaded in " + lpparam.packageName
                + " process=" + lpparam.processName);

        try {
            XposedHelpers.findAndHookMethod(Dialog.class, "show", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    Object object = param.thisObject;
                    if (!(object instanceof AlertDialog)) {
                        return;
                    }

                    try {
                        AlertDialog dialog = (AlertDialog) object;
                        Object controller = XposedHelpers.getObjectField(dialog, "mAlert");
                        Object titleObject = XposedHelpers.getObjectField(controller, "mTitle");
                        Object messageObject = XposedHelpers.getObjectField(controller, "mMessage");

                        String title = titleObject == null ? "" : titleObject.toString();
                        String message = messageObject == null ? "" : messageObject.toString();

                        boolean matches = title.contains(TITLE_FRAGMENT)
                                && message.contains(MESSAGE_FRAGMENT_1)
                                && message.contains(MESSAGE_FRAGMENT_2);

                        if (matches) {
                            XposedBridge.log("[NTCodePopupBlocker] MATCH: blocking LG NT-Code warning.");
                            param.setResult(null);
                        } else if (title.contains(TITLE_FRAGMENT)
                                || message.contains(MESSAGE_FRAGMENT_1)) {
                            XposedBridge.log("[NTCodePopupBlocker] AlertDialog observed but text did not fully match; title="
                                    + title + ", message=" + message);
                        }
                    } catch (Throwable t) {
                        XposedBridge.log("[NTCodePopupBlocker] Could not inspect AlertDialog: " + t);
                    }
                }
            });
            XposedBridge.log("[NTCodePopupBlocker] Hook installed on android.app.Dialog.show().");
        } catch (Throwable t) {
            XposedBridge.log("[NTCodePopupBlocker] Hook install failed: " + t);
        }
    }
}
