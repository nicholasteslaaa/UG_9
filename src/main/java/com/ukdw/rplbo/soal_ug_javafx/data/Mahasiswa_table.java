package com.ukdw.rplbo.soal_ug_javafx.data;

import com.ukdw.rplbo.soal_ug_javafx.Database_manager;
import com.ukdw.rplbo.soal_ug_javafx.entity.Mahasiswa;
import com.ukdw.rplbo.soal_ug_javafx.entity.Matakuliah;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mahasiswa_table {
    Connection conn;

    public Mahasiswa_table() throws SQLException {
        this.conn = new Database_manager().getConn();
    }

    public List<Mahasiswa> fetch_all_mahasiswa(){
        List<Mahasiswa> result = new ArrayList<>();

        if (conn == null) {
            System.out.println("Connection is null!");
            return result;
        }

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from mahasiswa");
            while (rs.next()){
                String nim = rs.getString("NIM");
                String nama = rs.getString("nama");
                result.add(new Mahasiswa(nim,nama));
            }

        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
        return result;
    }

    public Mahasiswa fetch_mahasiswa_by_nim(String nim) {
        String query = "SELECT nim, nama FROM Mahasiswa WHERE nim = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nim);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Mapping columns to the object
                    return new Mahasiswa(
                            rs.getString("nim"),
                            rs.getString("nama")
                    );
                }
            }
        } catch (SQLException e) {
            // In a real app, consider logging this or throwing a custom exception
            System.err.println("Database error: " + e.getMessage());
        }

        return null; // Return null if student isn't found
    }

    public static void main(String[] args) throws SQLException {
        Mahasiswa_table mt = new Mahasiswa_table();
        for(Mahasiswa mhs : mt.fetch_all_mahasiswa()){
            System.out.println(mhs.getNIM()+" "+mhs.getNama()+" "+mhs.getAngkatan());
        }
    }
}
