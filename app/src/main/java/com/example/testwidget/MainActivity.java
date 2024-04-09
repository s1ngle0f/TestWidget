package com.example.testwidget;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Log.d("PERMISSION_123", "GRANTED");
                } else {
                    Log.d("PERMISSION_123", "NOT GRANTED");
                }
            });
    private ActivityResultLauncher<String[]> multiRequestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), callback -> {
                for (Map.Entry<String, Boolean> entry : callback.entrySet()) {
                    String key = entry.getKey();
                    Boolean value = entry.getValue();
                    Log.d("PERMISSION_123", key + " " + value);
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissionLauncher.launch(Manifest.permission.CAMERA);
        multiRequestPermissionLauncher.launch(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA});
    }
}