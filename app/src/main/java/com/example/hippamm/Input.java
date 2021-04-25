package com.example.hippamm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hippamm.mysql.AppController;
import com.example.hippamm.mysql.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import pl.aprilapps.easyphotopicker.EasyImage;

public class Input extends AppCompatActivity {

    private ImageView setImage;
    private Button OpenImage;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText btDatePicker;
    private TextView tvDateResult;
    File imgFile;
    Button btnKirim;
    EditText txtTahun, txtBulan, txtTanggalCatat, txtJumlahMeter, txtPegawai;
    String idPelanggan, idGolongan, namaPegawai;
    int pegawai_id;

    //Request Code Digunakan Untuk Menentukan Permintaan dari User
    public static final int REQUEST_CODE_CAMERA = 001;
    public static final int REQUEST_CODE_GALLERY = 002;
    public static final String TAG = AppController.class.getSimpleName();
    private String url = Server.URL + "tagihan/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Bundle extras = getIntent().getExtras();
        idPelanggan = extras.getString("idPelanggan");
        idGolongan = extras.getString("idGolongan");
        pegawai_id = extras.getInt("pegawai_id");
        namaPegawai = extras.getString("namaPegawai");

        setImage = findViewById(R.id.showImg);
        OpenImage = findViewById(R.id.btnKamera);
        btnKirim = findViewById(R.id.btnSend);
        txtTahun = findViewById(R.id.txtTahun);
        txtBulan = findViewById(R.id.txtBulan);
        txtTanggalCatat = findViewById(R.id.txtTanggalCatat);
        txtJumlahMeter = findViewById(R.id.txtJumlahMeter);
        txtPegawai = findViewById(R.id.txtPegawai);

        txtPegawai.setText(namaPegawai);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToMysql();
            }
        });

        OpenImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRequestImage();
            }
        });

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        tvDateResult = (TextView) findViewById(R.id.txtTanggalCatat);
        btDatePicker = (EditText) findViewById(R.id.txtTanggalCatat);
        btDatePicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showDateDialog();
                return false;
            }
        });

        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogImage();
            }
        });
    }

    //show date picker
    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    //create
    void addToMysql(){
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    int success = jObj.getInt("success");
                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());
                        Toast.makeText(Input.this, jObj.getString("Data berhasil di simpan!"), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Input.this, jObj.getString("Data tidak berhasil di simpan!"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(Input.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idPelanggan", idPelanggan);
                params.put("idPegawai", Integer.toString(pegawai_id));
                params.put("idGolongan", idGolongan);
                params.put("tahun", txtTahun.getText().toString());
                params.put("bulan", txtBulan.getText().toString());
                params.put("tanggalCatat", txtTanggalCatat.getText().toString());
                params.put("jumlahMeter", txtJumlahMeter.getText().toString());
                params.put("fotoMeteran", "fotoMeteran");
                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, "json_obj_req");
    }

    //menampilkan gambar
    void showDialogImage(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_image);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //Button dialogButton = (Button) dialog.findViewById(R.id.ok);
        ImageView imgView = (ImageView) dialog.findViewById(R.id.imageView);
        Uri uri = Uri.fromFile(imgFile);
        imgView.setImageURI(uri);

        Button cancelButton = (Button) dialog.findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //Membuka Image dari Galeri atau Kamera
    private void setRequestImage(){
        CharSequence[] item = {"Kamera", "Galeri"};
        AlertDialog.Builder request = new AlertDialog.Builder(this)
                .setTitle("Add Image")
                .setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                //Membuka Kamera Untuk Mengambil Gambar
                                EasyImage.openCamera(Input.this, REQUEST_CODE_CAMERA);
                                break;
                            case 1:
                                //Membuaka Galeri Untuk Mengambil Gambar
                                EasyImage.openGallery(Input.this, REQUEST_CODE_GALLERY);
                                break;
                        }
                    }
                });
        request.create();
        request.show();
    }

    //Method Ini Digunakan Untuk Menapatkan Hasil pada Activity, dari Proses Yang kita buat sebelumnya
    //Dan Mendapatkan Hasil File Photo dari Galeri atau Kamera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Method Ini Digunakan Untuk Menghandle Error pada Image
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                //Method Ini Digunakan Untuk Menghandle Image
                imgFile = imageFile;
                switch (type){
                    case REQUEST_CODE_CAMERA:
                        Glide.with(Input.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(setImage);
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with(Input.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(setImage);
                        break;
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Batalkan penanganan, Anda mungkin ingin menghapus foto yang diambil jika dibatalkan
            }
        });
    }

}