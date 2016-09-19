package com.narvancomputer.perm.busevent;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;

public final class BusProvider extends Bus {

    private static BusProvider instance;

    public static BusProvider getInstance() {

        if (instance == null)
            instance = new BusProvider();

        return instance;
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {


        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    BusProvider.getInstance().post(event);
                }
            });
        }


    }
}
