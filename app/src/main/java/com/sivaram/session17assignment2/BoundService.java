package com.sivaram.session17assignment2;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 27/10/2017.
 */

public class BoundService extends Service{

    // Initailize Binder.
    IBinder binder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    // Set Instanace of Bind Servce And Return the current class
    public class LocalBinder extends Binder {
        public BoundService getServerInstance() {
            return BoundService.this;
        }
    }

    // Get Local System Time And return.
    public String getTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return mDateFormat.format(new Date());
    }
}
