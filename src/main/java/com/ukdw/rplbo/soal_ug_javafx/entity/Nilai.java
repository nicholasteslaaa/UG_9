package com.ukdw.rplbo.soal_ug_javafx.entity;

public class Nilai {
    String NIM;
    String kode_mk;
    String nilai;

    public Nilai(String nim, String kode_mk, String nilai){
        this.NIM = nim;
        this.kode_mk = kode_mk;
        this.nilai = nilai;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getKode_mk() {
        return kode_mk;
    }

    public void setKode_mk(String kode_mk) {
        this.kode_mk = kode_mk;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public double get_converted_nilai(){
        if (this.nilai.equals("A")){
            return 4.0;
        }
        if (this.nilai.equals("A-")){
            return 3.7;
        }
        if (this.nilai.equals("B+")){
            return 3.3;
        }
        if (this.nilai.equals("B")){
            return 3.0;
        }
        if (this.nilai.equals("B-")){
            return 2.7;
        }
        if (this.nilai.equals("C+")){
            return 2.3;
        }
        if (this.nilai.equals("C")){
            return 2.0;
        }
        if (this.nilai.equals("D")){
            return 1.0;
        }
        else{
            return 0.0;
        }
    }
}
