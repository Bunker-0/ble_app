package com.example.fake_ble_key;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView debugText = (TextView) findViewById(R.id.debug);
        Button Trigger = (Button) findViewById(R.id.trigger_button);

        Trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//                debugText.setText(mBluetoothAdapter.getName());
//                mBluetoothAdapter.startDiscovery();
//                BluetoothLeScanner mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
//                mBluetoothLeScanner.startScan(new ScanCallback() {
//
//                    @Override
//                    public void onBatchScanResults(List<ScanResult> results) {
//                        super.onBatchScanResults(results);
//                    }
//
//                    @Override
//                    public void onScanFailed(int errorCode) {
//                        super.onScanFailed(errorCode);
//                        debugText.setText(String.valueOf(errorCode));
//                    }
//
//                    @Override
//                    public void onScanResult(int callbackType, ScanResult result) {
//                        super.onScanResult(callbackType, result);
//                        debugText.setText(result.getDevice().getName());
//                    }
//                });
                mBluetoothAdapter.startDiscovery();
            }
        });

        BroadcastReceiver BleReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    String deviceName = device.getName();
                    debugText.setText(deviceName);
                }
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(BleReceiver, filter);
    }
}