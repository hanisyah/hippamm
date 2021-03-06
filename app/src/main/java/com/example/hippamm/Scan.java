package com.example.hippamm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    int pegawai_id;
    String namaPegawai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        Bundle extras = getIntent().getExtras();
        pegawai_id = extras.getInt("pegawai_id");
        namaPegawai = extras.getString("namaPegawai");
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        /*Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
         */

        Intent intent = new Intent(getBaseContext(), TampilPelanggan.class);
        intent.putExtra("idPelanggan", rawResult.getText());
        intent.putExtra("pegawai_id", pegawai_id);
        intent.putExtra("namaPegawai", namaPegawai);
        startActivity(intent);
        mScannerView.resumeCameraPreview(this);
        //finish();
    }

}