package com.narvancomputer.perm.busevent;

import java.util.ArrayList;

public class PermissionerEvent {

    public boolean permission;
    public ArrayList<String> deniedPermissions;


    public PermissionerEvent(boolean permission, ArrayList<String> deniedPermissions
    ) {
        this.permission = permission;
        this.deniedPermissions = deniedPermissions;
    }


    public boolean hasPermission() {
        return permission;
    }


    public ArrayList<String> getDeniedPermissions() {
        return deniedPermissions;
    }
}
