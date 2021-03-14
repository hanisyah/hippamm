package com.example.hippamm.mysql;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hippamm.R;

import java.util.List;

public class adapterPelanggan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<dataPelanggan> items;

    public adapterPelanggan(Activity activity, List<dataPelanggan> items) {
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
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        /*if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);*/

        //TextView idPelanggan = (TextView) convertView.findViewById(R.id.id);
        TextView namaPelanggan = (TextView) convertView.findViewById(R.id.txtNamaPelanggan);
        TextView alamat = (TextView) convertView.findViewById(R.id.txtAlamat);
        TextView noHP = (TextView) convertView.findViewById(R.id.txtAlamat);
        TextView tanggalPasang = (TextView) convertView.findViewById(R.id.txtTanggalPasang);
        TextView kodeMeter = (TextView) convertView.findViewById(R.id.txtKodeMeter);

        dataPelanggan dataPelanggan = items.get(position);

        //id.setText(dataPelanggan.getIdPelanggan());
        namaPelanggan.setText(dataPelanggan.getNamaPelanggan());
        alamat.setText(dataPelanggan.getAlamat());
        noHP.setText(dataPelanggan.getNoHP());
        tanggalPasang.setText(dataPelanggan.getTanggalPasang());
        kodeMeter.setText(dataPelanggan.getKodeMeter());

        return convertView;
    }
}
