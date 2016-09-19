package com.narvancomputer.perm;

import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;

public class Permissioner {

    private static Instance instance;


    public Permissioner(Context context) {
        instance = new Instance(context);
    }

    public Permissioner setPermissionListener(PermissionListener listener) {

        instance.listener = listener;

        return this;
    }


    public Permissioner setPermissions(String... permissions) {

        instance.permissions = permissions;
        return this;
    }

    public Permissioner setRationaleMessage(String rationaleMessage) {

        instance.rationaleMessage = rationaleMessage;
        return this;
    }


    public Permissioner setRationaleMessage(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for RationaleMessage");

        instance.rationaleMessage = instance.context.getString(stringRes);
        return this;
    }


    public Permissioner setDeniedMessage(String denyMessage) {

        instance.denyMessage = denyMessage;
        return this;
    }


    public Permissioner setDeniedMessage(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for DeniedMessage");

        instance.denyMessage = instance.context.getString(stringRes);
        return this;
    }


    public Permissioner setGotoSettingButton(boolean hasSettingBtn) {

        instance.hasSettingBtn = hasSettingBtn;
        return this;
    }


    public Permissioner setGotoSettingButtonText(String rationaleConfirmText) {

        instance.settingButtonText = rationaleConfirmText;
        return this;
    }


    public Permissioner setGotoSettingButtonText(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for setGotoSettingButtonText");


        instance.settingButtonText = instance.context.getString(stringRes);

        return this;
    }


    public Permissioner setRationaleConfirmText(String rationaleConfirmText) {

        instance.rationaleConfirmText = rationaleConfirmText;
        return this;
    }


    public Permissioner setRationaleConfirmText(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for RationaleConfirmText");


        instance.rationaleConfirmText = instance.context.getString(stringRes);

        return this;
    }


    public Permissioner setDeniedCloseButtonText(String deniedCloseButtonText) {

        instance.deniedCloseButtonText = deniedCloseButtonText;
        return this;
    }


    public Permissioner setDeniedCloseButtonText(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for DeniedCloseButtonText");


        instance.deniedCloseButtonText = instance.context.getString(stringRes);

        return this;
    }


    public void check() {
        if (instance.listener == null) {
            throw new NullPointerException("You must setPermissionListener() on Permissioner");
        } else if (Utils.isEmpty(instance.permissions)) {
            throw new NullPointerException("You must setPermissions() on Permissioner");
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            instance.listener.onPermissionGranted();
        } else {
            instance.checkPermissions();
        }


    }


}
