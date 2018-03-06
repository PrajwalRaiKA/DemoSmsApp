package com.example.prajwalrai.myapplication;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prajwalrai.myapplication.adapters.SmsAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView smsRecyler;
    private TextView tvEmpty;
    private MainViewController viewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smsRecyler = findViewById(R.id.rv_sms_list);
        tvEmpty = findViewById(R.id.tv_empty_view);

        viewController = new MainViewController(this);
        smsRecyler.setLayoutManager(new LinearLayoutManager(this));
        fetchAllSmsFromDevice();

    }

    private void fetchAllSmsFromDevice() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
                viewController.getAllSms();
            } else {
                final int REQUEST_CODE_ASK_PERMISSIONS = 123;
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
            }

        } else {
            viewController.getAllSms();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewController.getAllSms();
            } else {
                showEmptyView();
                Toast.makeText(this, "Insufficient Permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void showSms(LinkedHashMap<String, ArrayList<Sms>> smsHashMap) {
        smsRecyler.setAdapter(new SmsAdapter(smsHashMap, this));
    }

    public void hideEmptyView() {
        tvEmpty.setVisibility(View.GONE);
        smsRecyler.setVisibility(View.VISIBLE);
    }


    public void showEmptyView() {
        tvEmpty.setVisibility(View.VISIBLE);
        smsRecyler.setVisibility(View.GONE);
    }
}
