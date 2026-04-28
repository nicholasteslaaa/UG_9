package com.ukdw.rplbo.soal_ug_javafx.entity;

public class Matakuliah {
    String kode_mk;
    String nama;
    int sks;

    public Matakuliah(String kode_mk, String nama, int sks){
        this.kode_mk = kode_mk;
        this.nama = nama;
        this.sks = sks;
    }

    public String getKode_mk() {
        return kode_mk;
    }

    public void setKode_mk(String kode_mk) {
        this.kode_mk = kode_mk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

}
