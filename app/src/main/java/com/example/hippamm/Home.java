package com.example.hippamm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imageInput = findViewById(R.id.imgInput);
        TextView textInput = findViewById(R.id.txtInput);
        ImageView imageLihat = findViewById(R.id.imgLihat);
        TextView textLihat = findViewById(R.id.txtLihat);

        imageInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Scan.class));
            }
        });

        textInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Scan.class));
            }
        });
    }
}