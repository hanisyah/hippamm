package com.example.hippamm.mysql;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hippamm.Input;
import com.example.hippamm.R;
import com.example.hippamm.TampilTagihan;

import java.text.ParseException;
import java.util.List;

public class adapterTagihan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<dataTagihan> items;

    public adapterTagihan(Activity activity, List<dataTagihan> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.activity_tampil_tagihan2, null);

        TextView kodeMeter = (TextView) convertView.findViewById(R.id.txtKodeMeter);
        TextView tahun = (TextView) convertView.findViewById(R.id.txtTahun);
        TextView bulan = (TextView) convertView.findViewById(R.id.txtBulan);
        TextView namaPelanggan = (TextView) convertView.findViewById(R.id.txtNamaPelanggan);
        TextView namaGolongan = (TextView) convertView.findViewById(R.id.txtGolongan);
        TextView tanggalCatat = (TextView) convertView.findViewById(R.id.txtTanggalCatat);
        TextView jumlahMeter = (TextView) convertView.findViewById(R.id.txtJumlahMeter);
        ImageView fotoMeteran = (ImageView) convertView.findViewById(R.id.fotoMeter);
        TextView namaPegawai = (TextView) convertView.findViewById(R.id.txtPegawaiPencatat);
        TextView idGolongan = (TextView) convertView.findViewById(R.id.txtIdGolongan);
        TextView idPelanggan = (TextView) convertView.findViewById(R.id.txtIdPelanggan);
        TextView idTagihan = (TextView) convertView.findViewById(R.id.txtIdTagihan);
        TextView txtImage = (TextView) convertView.findViewById(R.id.txtImage);

        dataTagihan dataTagihan = items.get(position);

        kodeMeter.setText(dataTagihan.getKodeMeter());
        tahun.setText(dataTagihan.getTahun());
        bulan.setText(dataTagihan.getBulan());
        namaPelanggan.setText(dataTagihan.getNamaPelanggan());
        namaGolongan.setText(dataTagihan.getNamaGolongan());
        try {
            tanggalCatat.setText(dataTagihan.getTanggalCatat());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jumlahMeter.setText(dataTagihan.getJumlahMeter());
        //fotoMeteran.setText(dataTagihan.getFotoMeteran());
        namaPegawai.setText(dataTagihan.getNamaPegawai());
        idGolongan.setText(dataTagihan.getIdGolongan());
        idPelanggan.setText(dataTagihan.getIdPelanggan());
        idTagihan.setText(Integer.toString(dataTagihan.getIdTagihan()));
        txtImage.setText(dataTagihan.getFotoMeteran());

        Glide.with(convertView.getContext())
                .load(Server.URL + "img/" + dataTagihan.getFotoMeteran())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fotoMeteran);

        return convertView;
    }

}

