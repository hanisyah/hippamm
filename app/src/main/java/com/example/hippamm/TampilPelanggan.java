package com.example.hippamm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hippamm.mysql.AppController;
import com.example.hippamm.mysql.Server;
import com.example.hippamm.mysql.adapterPelanggan;
import com.example.hippamm.mysql.dataPelanggan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TampilPelanggan extends AppCompatActivity {

    private String url = Server.URL + "api/pelanggan";
    private static final String TAG = TampilPelanggan.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    adapterPelanggan adapterPelanggan;
    String idPelanggan;
    List<dataPelanggan> itemList = new ArrayList<dataPelanggan>();
    TextView namaPelanggan, alamat, noHP, tanggalPasang, kodeMeter, golongan;
    int pegawai_id;
    String namaPegawai, golongan_id;
    String[] bulanIndo = {"", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pelanggan);

        Bundle extras = getIntent().getExtras();
        idPelanggan = extras.getString("idPelanggan");
        pegawai_id = extras.getInt("pegawai_id");
        namaPegawai = extras.getString("namaPegawai");

        adapterPelanggan = new adapterPelanggan(TampilPelanggan.this, itemList);
        namaPelanggan = (TextView) findViewById(R.id.txtNamaPelanggan);
        alamat = (TextView) findViewById(R.id.txtAlamat);
        noHP = (TextView) findViewById(R.id.txtNoHP);
        tanggalPasang = (TextView) findViewById(R.id.txtTanggalPasang);
        kodeMeter = (TextView) findViewById(R.id.txtKodeMeter);
        golongan = (TextView) findViewById(R.id.txtGolongan);

        checkPelanggan();

        Button buttonInput = findViewById(R.id.btnInput);

        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Input.class);
                intent.putExtra("idPelanggan", idPelanggan);
                intent.putExtra("pegawai_id", pegawai_id);
                intent.putExtra("namaPegawai", namaPegawai);
                intent.putExtra("idGolongan", golongan_id);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void checkPelanggan() {
         StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONArray jObj = new JSONArray(response);
                    JSONObject obj = jObj.getJSONObject(0);

                    namaPelanggan.setText(obj.getString("namaPelanggan"));
                    alamat.setText(obj.getString("alamat"));
                    noHP.setText(obj.getString("noHP"));
                    tanggalPasang.setText(obj.getString("tanggalPasang"));

                    String[] tgl = obj.getString("tanggalPasang").split("-");
                    tanggalPasang.setText( tgl[2] + " " + bulanIndo[Integer.parseInt(tgl[1])] + " " +tgl[0]);

                    kodeMeter.setText(obj.getString("kodeMeter"));
                    golongan.setText(obj.getString("namaGolongan"));
                    golongan_id = obj.getString("golongan_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(TampilPelanggan.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idPelanggan", idPelanggan);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}