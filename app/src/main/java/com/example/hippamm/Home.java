package com.example.hippamm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    int pegawai_id;
    String namaPegawai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imageInput = findViewById(R.id.imgInput);
        TextView textInput = findViewById(R.id.txtInput);
        ImageView imageLihat = findViewById(R.id.imgLihat);
        TextView textLihat = findViewById(R.id.txtLihat);

        Bundle extras = getIntent().getExtras();
        pegawai_id = extras.getInt("pegawai_id");
        namaPegawai = extras.getString("namaPegawai");

        imageInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Scan.class);
                intent.putExtra("pegawai_id", pegawai_id);
                intent.putExtra("namaPegawai", namaPegawai);
                startActivity(intent);
                finish();
            }
        });

        textInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Scan.class);
                intent.putExtra("pegawai_id", pegawai_id);
                intent.putExtra("namaPegawai", namaPegawai);
                startActivity(intent);
                finish();
            }
        });

        imageLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TampilTagihan.class);
                intent.putExtra("pegawai_id", pegawai_id);
                intent.putExtra("namaPegawai", namaPegawai);
                startActivity(intent);
                finish();
            }
        });

        textLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TampilTagihan.class);
                startActivity(intent);
                finish();
            }
        });

    }
}