package com.example.hippamm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class Home extends AppCompatActivity {

    int pegawai_id;
    String namaPegawai;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imageInput = findViewById(R.id.imgInput);
        TextView textInput = findViewById(R.id.txtInput);
        ImageView imageLihat = findViewById(R.id.imgLihat);
        TextView textLihat = findViewById(R.id.txtLihat);
        ImageView buttonLogout = findViewById(R.id.btnLogout);

        sessionManager = new SessionManager( Home.this);
        HashMap<String,String> user = sessionManager.getUserDetail();

//        Bundle extras = getIntent().getExtras();
        pegawai_id = Integer.parseInt(user.get(SessionManager.ID));
        namaPegawai = user.get(SessionManager.USERNAME);

        imageInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Scan.class);
                intent.putExtra("pegawai_id", pegawai_id);
                intent.putExtra("namaPegawai", namaPegawai);
                startActivity(intent);
                //finish();
            }
        });

        textInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Scan.class);
                intent.putExtra("pegawai_id", pegawai_id);
                intent.putExtra("namaPegawai", namaPegawai);
                startActivity(intent);
                //finish();
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

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sessionManager.logout();
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });
    }
    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Logout")
                .setMessage("Apakah yakin akan Logout?")
                .setIcon(R.drawable.logout)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        sessionManager.logout();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }



//    private void moveToLogin(){
//        Intent intent = new Intent(Home.this, Login.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
//        startActivity(intent);
//        finish();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item){
//        switch (item.getItemId()) {
//            case R.id.btnLogout:
//                sessionManager.logoutSession();
//                moveToLogin();
//        }
//        return super.onOptionsItemSelected(item);
//    }

}