package com.sivaram.session17assignment2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Check whether service is binded.
    boolean isBounded;
    // Declare BindService Class Object.
    BoundService boundService;
    // TextView Object
    TextView timeTextView;

    // Button Object.
    Button changeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intialize TextView
        timeTextView = (TextView)findViewById(R.id.currentTimeTextView);

        // Initialize Button
        changeTime = (Button) findViewById(R.id.changeTimeButton);

        // Set OnClick Listener and set get Time From Service Class
        changeTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                timeTextView.setText(boundService.getTime());
            }
        });
    }

    // On Service Start Create And Intent And Initailize BoundService Class
    @Override
    protected void onStart() {
        super.onStart();

        // Create Intent
        Intent mIntent = new Intent(this, BoundService.class);

        // Intialize Bind Service.
        bindService(mIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    // Create Service Connection Object and Check Connect and Disconnect Services.
    ServiceConnection serviceConnection = new ServiceConnection() {

        // On Service Disconnect. reset varaibles.
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "Service Ended", Toast.LENGTH_SHORT).show();
            isBounded = false;
            boundService = null;
        }

        // On Service Start Call Bind Service And Create Instanace.
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "Service Started", Toast.LENGTH_SHORT).show();
            isBounded = true;
            BoundService.LocalBinder localBinder = (BoundService.LocalBinder)service;
            boundService = localBinder.getServerInstance();
        }
    };

    // On Stop Service Close The Service.
    @Override
    protected void onStop() {
        super.onStop();
        if(isBounded) {
            unbindService(serviceConnection);
            isBounded = false;
        }
    }
}
