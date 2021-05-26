package com.example.hippamm.mysql;

public class dataTagihan {
    private int idTagihan;
    private String tanggalCatat, tahun, bulan, jumlahMeter, fotoMeteran, idPelanggan, kodeMeter, namaPelanggan, idGolongan, namaGolongan, idPegawai, namaPegawai;

    public dataTagihan() {
    }

    public dataTagihan(int idTagihan, String tanggalCatat, String tahun, String bulan, String jumlahMeter, String fotoMeteran, String idPelanggan, String kodeMeter, String namaPelanggan, String idGolongan, String namaGolongan, String idPegawai, String namaPegawai) {
        this.idTagihan = idTagihan;
        this.tanggalCatat = tanggalCatat;
        this.tahun = tahun;
        this.bulan = bulan;
        this.jumlahMeter = jumlahMeter;
        this.fotoMeteran = fotoMeteran;
        this.idPelanggan = idPelanggan;
        this.kodeMeter = kodeMeter;
        this.namaPelanggan = namaPelanggan;
        this.idGolongan = idGolongan;
        this.namaGolongan = namaGolongan;
        this.idPegawai = idPegawai;
        this.namaPegawai = namaPegawai;
    }

    public int getIdTagihan() {
        return idTagihan;
    }

    public void setIdTagihan(int idTagihan) {
        this.idTagihan = idTagihan;
    }

    public String getTanggalCatat() { return tanggalCatat; }

    public void setTanggalCatat(String tanggalCatat) { this.tanggalCatat = tanggalCatat; }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getJumlahMeter() {
        return jumlahMeter;
    }

    public void setJumlahMeter(String jumlahMeter) {
        this.jumlahMeter = jumlahMeter;
    }

    public String getFotoMeteran() {
        return fotoMeteran;
    }

    public void setFotoMeteran(String fotoMeteran) {
        this.fotoMeteran = fotoMeteran;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getKodeMeter() {
        return kodeMeter;
    }

    public void setKodeMeter(String kodeMeter) {
        this.kodeMeter = kodeMeter;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getIdGolongan() {
        return idGolongan;
    }

    public void setIdGolongan(String idGolongan) {
        this.idGolongan = idGolongan;
    }

    public String getNamaGolongan() {
        return namaGolongan;
    }

    public void setNamaGolongan(String namaGolongan) {
        this.namaGolongan = namaGolongan;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }
}
