package com.rewass.download;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestForWriteExternalStorge();


            }
        });

    }

    private void requestForWriteExternalStorge() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }

    private void download() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://dl.musickordi.com/hasan-zirak/Hasan-Zirak_Are-Bena.mp3"));
        request.setTitle("دانلودی حەسەن زیرەک");

        request.setDescription("ناوی گۆرانیەکەی");
        request.setAllowedNetworkTypes(2 | 1); //2 is wifi and 1 is network of phone
        // request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI); //this line have same meaning to 1 and 2
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis()); //Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis() this is my download path
        request.setNotificationVisibility(1);//send notification after finish download
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE); //download one by one
        downloadManager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0) {
                    download();


                } else {

                    Toast.makeText(MainActivity.this, "دسترسی به حافظه داخلی مورد نیاز است", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

}