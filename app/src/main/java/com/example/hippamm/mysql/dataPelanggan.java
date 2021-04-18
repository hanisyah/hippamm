package com.example.hippamm.mysql;

public class dataPelanggan {
    private int idPelanggan;
    private String namaPelanggan, alamat, noHP, tanggalPasang, kodeMeter, idGolongan, namaGolongan;

    public dataPelanggan() {
    }

    public dataPelanggan(int idPelanggan, String namaPelanggan, String alamat, String noHP, String tanggalPasang, String kodeMeter, String idGolongan, String namaGolongan) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.alamat = alamat;
        this.noHP = noHP;
        this.tanggalPasang = tanggalPasang;
        this.kodeMeter = kodeMeter;
        this.idGolongan = idGolongan;
        this.namaGolongan = namaGolongan;
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
}
