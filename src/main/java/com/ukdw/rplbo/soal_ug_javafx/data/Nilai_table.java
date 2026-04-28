package com.ukdw.rplbo.soal_ug_javafx.data;

import com.ukdw.rplbo.soal_ug_javafx.Database_manager;
import com.ukdw.rplbo.soal_ug_javafx.entity.Nilai;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Nilai_table {
    Connection conn;
    public String[] penilaian = {"A","A-","B+","B","B-","C+","C","D","E"};

    public Nilai_table() throws SQLException {
        this.conn = new Database_manager().getConn();
    }

    public List<Nilai> fetch_all_nilai(){
        List<Nilai> result = new ArrayList<>();
        if (conn == null) {
            System.out.println("Connection is null!");
            return result;
        }

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM nilai");
            while (rs.next()){
                String nim = rs.getString("nim");
                String kode_mk = rs.getString("kode_mk");
                String nilai = rs.getString("nilai");
                result.add(new Nilai(nim, kode_mk, nilai));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all nilai: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public List<Nilai> fetch_nilai_by(String target_col,String value) {
        List<Nilai> result = new ArrayList<>();
        String query = "SELECT * FROM nilai WHERE "+ target_col + " = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, value); // bind parameter

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nimVal = rs.getString("nim");
                String kodeMk = rs.getString("kode_mk");
                String nilaiVal = rs.getString("nilai");

                result.add(new Nilai(nimVal, kodeMk, nilaiVal));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }

        return result;
    }

    public List<Nilai> fetch_nilai_by_kode_mk(String kode_mk) {
        List<Nilai> result = new ArrayList<>();
        String query = "SELECT * FROM nilai WHERE kode_mk = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, kode_mk); // bind parameter

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nimVal = rs.getString("nim");
                String kodeMk = rs.getString("kode_mk");
                String nilaiVal = rs.getString("nilai");

                result.add(new Nilai(nimVal, kodeMk, nilaiVal));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }

        return result;
    }

    public static void main(String[] args) throws SQLException {
        Nilai_table nt = new Nilai_table();

        for (Nilai n: nt.fetch_all_nilai()){
            System.out.println(n.getNIM()+" "+n.getKode_mk()+" "+n.getNilai());
        }
    }

}
