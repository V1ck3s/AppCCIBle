package com.victor.appccible;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class DeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        Intent intent = getIntent();
        BluetoothDevice bluetoothDevice = intent.getParcelableExtra("TREATMENT_DETAILS");
        final TextView tvServices = (TextView) findViewById(R.id.textView_device_services);
        tvServices.setText(bluetoothDevice.getUuids().toString());
    }
}
