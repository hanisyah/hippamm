package com.example.hippamm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

    private String url = Server.URL + "pelanggan";
    private static final String TAG = TampilPelanggan.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    adapterPelanggan adapterPelanggan;
    String idPelanggan;
    List<dataPelanggan> itemList = new ArrayList<dataPelanggan>();
    TextView namaPelanggan, alamat, noHP, tanggalPasang, kodeMeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pelanggan);

        Bundle extras = getIntent().getExtras();
        idPelanggan = extras.getString("idPelanggan");

        adapterPelanggan = new adapterPelanggan(TampilPelanggan.this, itemList);
        namaPelanggan = (TextView) findViewById(R.id.txtNamaPelanggan);
        alamat = (TextView) findViewById(R.id.txtAlamat);
        noHP = (TextView) findViewById(R.id.txtNoHP);
        tanggalPasang = (TextView) findViewById(R.id.txtTanggalPasang);
        kodeMeter = (TextView) findViewById(R.id.txtKodeMeter);

        checkPelanggan();
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
                    kodeMeter.setText(obj.getString("kodeMeter"));

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