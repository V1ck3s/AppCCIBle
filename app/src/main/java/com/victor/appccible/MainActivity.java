package com.victor.appccible;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_mainactivity_);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)



        final Button buttonScan = (Button) findViewById(R.id.button_mainactivity_startscan);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                    Toast.makeText(getApplicationContext(), "BLE don't supported", Toast.LENGTH_SHORT).show();
                    finish();
                }




                BleManager.getInstance()
                        .enableLog(true)
                        .setReConnectCount(1, 5000)
                        .setSplitWriteNum(20)
                        .setConnectOverTime(10000)
                        .setOperateTimeout(5000)
                        .init(getApplication());

                BleManager.getInstance().scan(new BleScanCallback() {
                    @Override
                    public void onScanFinished(List<BleDevice> scanResultList) {
                        mAdapter = new MainAdapter(getApplicationContext(), scanResultList);
                        recyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onScanStarted(boolean success) {

                    }

                    @Override
                    public void onScanning(BleDevice bleDevice) {
                        Log.d("","");
                    }
                });

            }
        });


    }
}
