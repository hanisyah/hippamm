package com.example.hippamm.mysql;

public class dataPelanggan {
    private int idPelanggan;
    private String namaPelanggan, alamat, noHP, tanggalPasang, kodeMeter;

    public dataPelanggan() {
    }

    public dataPelanggan(int idPelanggan, String namaPelanggan, String alamat, String noHP, String tanggalPasang, String kodeMeter) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.alamat = alamat;
        this.noHP = noHP;
        this.tanggalPasang = tanggalPasang;
        this.kodeMeter = kodeMeter;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getTanggalPasang() {
        return tanggalPasang;
    }

    public void setTanggalPasang(String tanggalPasang) {
        this.tanggalPasang = tanggalPasang;
    }

    public String getKodeMeter() {
        return kodeMeter;
    }

    public void setKodeMeter(String kodeMeter) {
        this.kodeMeter = kodeMeter;
    }
}
