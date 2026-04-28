package com.ukdw.rplbo.soal_ug_javafx.entity;

import com.ukdw.rplbo.soal_ug_javafx.Database_manager;
import com.ukdw.rplbo.soal_ug_javafx.data.Nilai_table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mahasiswa {
    private String NIM;
    private String nama;
    int angkatan;

    public Mahasiswa(String nim, String nama) throws SQLException {
        this.NIM = nim;
        this.nama = nama;
        char[] nim_tmp = this.NIM.toCharArray();
        this.angkatan = Integer.valueOf("20"+nim_tmp[2]+nim_tmp[3]);
    }

    public List<Nilai> get_daftar_nilai(){

        return new ArrayList<>();
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }

    public static void main(String[] args) throws SQLException {
        Mahasiswa m = new Mahasiswa("71231021","Nicsap");
        System.out.println(m.angkatan);
    }

}
