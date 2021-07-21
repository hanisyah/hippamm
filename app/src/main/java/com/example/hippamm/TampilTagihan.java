package com.example.hippamm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hippamm.mysql.AppController;
import com.example.hippamm.mysql.Server;
import com.example.hippamm.mysql.adapterTagihan;
import com.example.hippamm.mysql.dataTagihan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TampilTagihan extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private String url = Server.URL + "api/tampiltagihan/";

    ListView list;
    SwipeRefreshLayout swipe;
    List itemList = new ArrayList();
    adapterTagihan adapter;
    private static final String TAG = TampilTagihan.class.getSimpleName();
    int pegawai_id;
    String namaPegawai;
    String[] bulanIndo = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
//    String[] urutBulan = new String[12];
//    urutBulan["Januari"] = 1;
//    urutBulan['Februari'] = 2;
//    urutBulan['Maret'] = 3;
//    urutBulan['April'] = 4;
//    urutBulan['Mei'] = 5;
//    urutBulan['Juni'] = 6;
//    urutBulan['Juli'] = 7;
//    urutBulan['Agustus'] = 8;
//    urutBulan['September'] = 9;
//    urutBulan['Oktober'] = 10;
//    urutBulan['November'] = 11;
//    urutBulan['Desember'] = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_tagihan);
        list = (ListView) findViewById(R.id.list);
        swipe   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        Bundle extras = getIntent().getExtras();
        pegawai_id = extras.getInt("pegawai_id");
        namaPegawai = extras.getString("namaPegawai");

        SimpleDateFormat datemonth = new SimpleDateFormat("MM");

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new adapterTagihan(TampilTagihan.this, itemList);
        list.setAdapter(adapter);
        swipe.setOnRefreshListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idPelanggan = view.findViewById(R.id.txtIdPelanggan);
                TextView idGolongan = view.findViewById(R.id.txtIdGolongan);
                TextView idTagihan = view.findViewById(R.id.txtIdTagihan);
                TextView tahun = view.findViewById(R.id.txtTahun);
                TextView bulan = view.findViewById(R.id.txtBulan);
                TextView tanggal = view.findViewById(R.id.txtTanggalCatat);
                TextView jumlah = view.findViewById(R.id.txtJumlahMeter);
                TextView image = view.findViewById(R.id.txtImage);

                //if(datemonth.format(new Date()).equals(urutBulan[Integer.parseInt(bulan.getText().toString())]) + 1){
                    Intent intent = new Intent(getBaseContext(), Input.class);
                    intent.putExtra("idTagihan", idTagihan.getText().toString());
                    intent.putExtra("idPelanggan", idPelanggan.getText().toString());
                    intent.putExtra("pegawai_id", pegawai_id);
                    intent.putExtra("namaPegawai", namaPegawai);
                    intent.putExtra("idGolongan", idGolongan.getText().toString());
                    intent.putExtra("tahun", tahun.getText().toString());
                    intent.putExtra("bulan", bulan.getText().toString());
                    intent.putExtra("tanggal", tanggal.getText().toString());
                    intent.putExtra("jumlahMeter", jumlah.getText().toString());
                    intent.putExtra("fotoMeter", image.getText().toString());
                    intent.putExtra("status", "edit");
                    startActivity(intent);
                    //finish();
                //}
            }
        });

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );

    }
    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }
    // untuk menampilkan semua data pada listview
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(url + pegawai_id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        dataTagihan item = new dataTagihan();
                        item.setIdTagihan(obj.getInt("idTagihan"));
                        item.setTanggalCatat(obj.getString("tanggalCatat"));
                        item.setTahun(obj.getString("tahun"));
                        item.setBulan(obj.getString("bulan"));
                        item.setJumlahMeter(obj.getString("jumlahMeter"));
                        item.setFotoMeteran(obj.getString("fotoMeteran"));
                        item.setKodeMeter(obj.getString("kodeMeter"));
                        item.setNamaPelanggan(obj.getString("namaPelanggan"));
                        item.setNamaGolongan(obj.getString("namaGolongan"));
                        item.setNamaPegawai(obj.getString("namaPegawai"));
                        item.setIdPelanggan(obj.getString("pelanggan_id"));
                        item.setIdGolongan(obj.getString("golongan_id"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Error: " + e.getMessage());
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }
}