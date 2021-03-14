package com.example.hippamm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class TampilPelanggan extends AppCompatActivity {

    private String url = Server.URL + "pelanggan";
    private static final String TAG = TampilPelanggan.class.getSimpleName();
    int success;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public final static String TAG_ID = "id";
    String tag_json_obj = "json_obj_req";

    adapterPelanggan adapterPelanggan;
    String idPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pelanggan);

        Bundle extras = getIntent().getExtras();
        idPelanggan = extras.getString("idPelanggan");

        checkPelanggan();
    }

    private void checkPelanggan() {
         StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONObject myResponse = jObj.getJSONObject("data");
                    //JSONArray tsmresponse = (JSONArray) myResponse.get("listTsm");
                    //JSONArray resp = myResponse.getJSONArray("0");
                    JSONObject obj = myResponse.getJSONObject("0");
                    success = jObj.getInt(TAG_SUCCESS);
                    //Log.e("CEKKK", "Response: " + resp.toString());
                    if (success == 1) {
                        dataPelanggan item = new dataPelanggan();
                        item.setNamaPelanggan(obj.getString("namaPelanggan"));
                        item.setAlamat(obj.getString("alamat"));
                        item.setNoHP(obj.getString("noHP"));
                        item.setTanggalPasang(obj.getString("tanggalPasang"));
                        item.setKodeMeter(obj.getString("kodeMeter"));

                    } else {

                    }
                } catch (JSONException e) {
                    // JSON error
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
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                params.put("idPelanggan", idPelanggan);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}