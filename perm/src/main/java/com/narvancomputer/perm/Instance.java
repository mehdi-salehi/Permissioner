package com.narvancomputer.perm;

import android.content.Context;
import android.content.Intent;
import com.narvancomputer.perm.busevent.BusProvider;
import com.narvancomputer.perm.busevent.PermissionerEvent;
import com.squareup.otto.Subscribe;

public class Instance {

    public PermissionListener listener;
    public String[] permissions;
    public String rationaleMessage;
    public String denyMessage;
    public String settingButtonText;
    public boolean hasSettingBtn = true;

    public String deniedCloseButtonText;
    public String rationaleConfirmText;
    Context context;


    public Instance(Context context) {

        this.context = context;

        BusProvider.getInstance().register(this);

        deniedCloseButtonText = context.getString(R.string.permission_close);
        rationaleConfirmText = context.getString(R.string.permission_confirm);
    }


    public void checkPermissions() {
        Intent intent = new Intent(context, PermissionerActivity.class);
        intent.putExtra(PermissionerActivity.EXTRA_PERMISSIONS, permissions);

        intent.putExtra(PermissionerActivity.EXTRA_RATIONALE_MESSAGE, rationaleMessage);
        intent.putExtra(PermissionerActivity.EXTRA_DENY_MESSAGE, denyMessage);
        intent.putExtra(PermissionerActivity.EXTRA_PACKAGE_NAME, context.getPackageName());
        intent.putExtra(PermissionerActivity.EXTRA_SETTING_BUTTON, hasSettingBtn);
        intent.putExtra(PermissionerActivity.EXTRA_DENIED_DIALOG_CLOSE_TEXT, deniedCloseButtonText);
        intent.putExtra(PermissionerActivity.EXTRA_RATIONALE_CONFIRM_TEXT, rationaleConfirmText);
        intent.putExtra(PermissionerActivity.EXTRA_SETTING_BUTTON_TEXT, settingButtonText);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Subscribe
    public void onPermissionResult(PermissionerEvent event) {
        if (event.hasPermission()) {
            listener.onPermissionGranted();
        } else {
            listener.onPermissionDenied(event.getDeniedPermissions());
        }
        BusProvider.getInstance().unregister(this);
    }

}
