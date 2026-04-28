package com.ukdw.rplbo.soal_ug_javafx.data;

import com.ukdw.rplbo.soal_ug_javafx.Database_manager;
import com.ukdw.rplbo.soal_ug_javafx.entity.Matakuliah;
import com.ukdw.rplbo.soal_ug_javafx.entity.Nilai;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Matakuliah_table {
    Connection conn;

    public Matakuliah_table() throws SQLException {
        this.conn = new Database_manager().getConn();
    }

    public List<Matakuliah> fetch_all_matkul(){
        List<Matakuliah> result = new ArrayList<>();

        if (conn == null) {
            System.out.println("Connection is null!");
            return result;
        }

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from matakuliah");
            while (rs.next()){
                String kode_mk = rs.getString("kode_mk");
                String nama = rs.getString("nama");
                int sks = rs.getInt("sks");
                result.add(new Matakuliah(kode_mk,nama,sks));
            }

        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
        return result;
    }

    public List<Matakuliah> fetch_matkul_by_kode_mk(String kode_mk) {
        List<Matakuliah> result = new ArrayList<>();
        String query = "SELECT * FROM kode_mk WHERE kode_mk = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, kode_mk); // bind parameter

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String kodeMkVal = rs.getString("kode_mk");
                String namaVal = rs.getString("nama");
                int sksVal = rs.getInt("sks");

                result.add(new Matakuliah(kodeMkVal, namaVal, sksVal));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }

        return result;
    }



    public static void main(String[] args) throws SQLException {
        Matakuliah_table matkul = new Matakuliah_table();
        for (Matakuliah mtkl: matkul.fetch_all_matkul()){
            System.out.println(mtkl.getKode_mk()+" "+mtkl.getNama()+" "+mtkl.getSks());
        }
    }
}
