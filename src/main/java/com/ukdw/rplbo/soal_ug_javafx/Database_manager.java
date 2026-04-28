package com.ukdw.rplbo.soal_ug_javafx;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class Database_manager {
    Connection conn = null;

    public Database_manager(String path,String user,String password) throws SQLException {
        String url = "jdbc:sqlite:"+path;
        this.conn = DriverManager.getConnection(url, user, password);
        if (this.conn != null) {
            System.out.println("Successfully connected to SQLite database at: " + path);
        }

        init_table();
        seedData();
    }

    public Database_manager(String path) throws SQLException {
        this(path,"root","");
    }

    public Database_manager() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String dbPath = dotenv.get("DB_PATH");
        String user = dotenv.get("DB_USER");
        String pass = dotenv.get("DB_PASS");
        System.out.println(dbPath);
        // Memanggil constructor utama pada objek yang sama
        String url = "jdbc:sqlite:" + dbPath;
        this.conn = DriverManager.getConnection(url, user, pass);
        if (this.conn != null) {
            System.out.println("Successfully connected to SQLite via .env at: " + dbPath);
        }
        init_table();
        seedData();
    }

    public void init_table() {
        String mahasiswaTable = """
                CREATE TABLE IF NOT EXISTS mahasiswa (
                    NIM TEXT,
                    nama TEXT
                );""";

        String matkulTable = """
               CREATE TABLE IF NOT EXISTS matakuliah (
                   kode_mk TEXT,
                   nama TEXT,
                   sks INTEGER
               );""";

        // PERBAIKAN: Hapus koma setelah TEXT dan ubah REAL menjadi TEXT
        String nilaiTable = """
               CREATE TABLE IF NOT EXISTS nilai (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nim TEXT,
                    kode_mk TEXT,
                    nilai TEXT
               );""";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(mahasiswaTable);
            stmt.execute(matkulTable);
            stmt.execute(nilaiTable);
            System.out.println("All tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void seedData() {
        String checkMatkul = "SELECT COUNT(*) FROM matakuliah";
        String checkMahasiswa = "SELECT COUNT(*) FROM mahasiswa";
        String checkNilai = "SELECT COUNT(*) FROM nilai";

        String matkul = "INSERT INTO matakuliah (kode_mk, nama, sks) VALUES\n"
                + "('MH1033', 'Bahasa Indonesia', 3),\n"
                + "('MH1013', 'Pendidikan Agama Kristen', 3),\n"
                + "('TI0013', 'Teknologi Komputer', 3),\n"
                + "('TI0022', 'Praktikum Teknologi Komputer', 2),\n"
                + "('TI0033', 'Matematika Teknik', 3),\n"
                + "('TI0043', 'Logika Matematika', 3),\n"
                + "('TI0203', 'Interaksi Manusia dan Komputer', 3),\n"
                + "('TI0073', 'Algoritma dan Pemrograman', 3),\n"
                + "('TI0082', 'Praktikum Algoritma dan Pemrograman', 2),\n"
                + "('TI0093', 'Matematika Diskrit', 3),\n"
                + "('TI0103', 'Arsitektur dan Organisasi Komputer', 3),\n"
                + "('TI0113', 'Statistik', 3),\n"
                + "('TI0052', 'Jaringan Komputer', 2),\n"
                + "('TI0061', 'Praktikum Jaringan Komputer', 1),\n"
                + "('MH1083', 'Pendidikan Kewarganegaraan', 3),\n"
                + "('TI0133', 'Struktur Data', 3),\n"
                + "('TI0141', 'Praktikum Struktur Data', 1),\n"
                + "('TI0153', 'Infrastruktur LAN', 3),\n"
                + "('TI0161', 'Praktikum Infrastruktur LAN', 1),\n"
                + "('TI0173', 'Sistem Basis Data – Kelas', 3),\n"
                + "('TI0181', 'Praktikum Sistem Basis Data', 1),\n"
                + "('TI0193', 'Riset Operasi', 3),\n"
                + "('TI0213', 'Sistem Operasi', 3),\n"
                + "('TI0373', 'Rekayasa Perangkat Lunak Berorientasi Obyek', 3),\n"
                + "('TI0382', 'Praktikum Rekayasa Perangkat Lunak Berorientasi Obyek', 2),\n"
                + "('TI0243', 'Pemrograman Web', 3),\n"
                + "('TI0251', 'Praktikum Pemrograman Web', 1),\n"
                + "('TI0263', 'Kecerdasan Buatan', 3),\n"
                + "('TI0273', 'Keamanan Komputer', 3),\n"
                + "('MH1073', 'Pendidikan Pancasila', 3),\n"
                + "('TI0302', 'Etika Profesi Teknologi Informasi', 2),\n"
                + "('TI0313', 'Manajemen Proyek Teknologi Informasi', 3),\n"
                + "('TI8033', 'Cloud Infrastructure', 3),\n"
                + "('TI8023', 'Enterprise Network', 3),\n"
                + "('TI8043', 'Pengantar Keamanan Jaringan', 3),\n"
                + "('TI8053', 'Jaringan Nir Kabel', 3),\n"
                + "('TI8063', 'Otomasi Jaringan', 3),\n"
                + "('TI8073', 'Teknologi WAN', 3),\n"
                + "('TI8083', 'Keamanan Jaringan', 3),\n"
                + "('TI6033', 'Internet of Things', 3),\n"
                + "('TI3013', 'Administrasi Basis Data', 3),\n"
                + "('TI3033', 'Data Warehouse', 3),\n"
                + "('TI3043', 'Basis Data Terdistribusi', 3),\n"
                + "('TI3053', 'Keamanan Basis Data', 3),\n"
                + "('TI3063', 'Administrasi Basis Data Non Relasional', 3),\n"
                + "('TI6113', 'Pemrograman Perangkat Bergerak Berbasis Android', 3),\n"
                + "('TI6123', 'Pemrograman Perangkat Bergerak Berbasis iOS', 3),\n"
                + "('TI6133', 'Pemrograman Perangkat Bergerak Berbasis Hybrid', 3),\n"
                + "('TI6143', 'Pemrograman Desktop', 3),\n"
                + "('TI6153', 'Pemrograman Web Lanjut', 3),\n"
                + "('TI5013', 'Pola Desain Antarmuka Pengguna', 3),\n"
                + "('TI5023', 'Desain Eksperimental', 3),\n"
                + "('TI5033', 'Desain dan Evaluasi Antarmuka', 3),\n"
                + "('TI4023', 'Pemodelan Proses Bisnis', 3),\n"
                + "('TI4033', 'Test Engineering', 3),\n"
                + "('TI6043', 'Machine Learning', 3),\n"
                + "('TI6053', 'Jaringan Syaraf Tiruan', 3),\n"
                + "('TI6063', 'Knowledge-Based System', 3),\n"
                + "('TI6073', 'Pemrosesan Bahasa Natural', 3),\n"
                + "('TI6083', 'Pemrosesan Citra Digital', 3),\n"
                + "('TI6093', 'Pemrosesan Sinyal Digital', 3),\n"
                + "('TI6103', 'Game Engine', 3),\n"
                + "('TI9013', 'Algoritma Graf', 3),\n"
                + "('TI9023', 'Analisis Data Statistik', 3),\n"
                + "('TI9383', 'Analisis Proses Bisnis', 3),\n"
                + "('TI9463', 'Bahasa Inggris Informatika', 3),\n"
                + "('TI9053', 'Competitive Programming', 3),\n"
                + "('TI9393', 'Deep Learning', 3),\n"
                + "('TI9063', 'Desain Game', 3),\n"
                + "('TI9073', 'Digital Humanities', 3),\n"
                + "('TI9403', 'E-Commerce', 3),\n"
                + "('TI9413', 'E-Government', 3),\n"
                + "('TI9083', 'Game Audio', 3),\n"
                + "('TI9103', 'Grafika Game', 3),\n"
                + "('TI9143', 'Kompresi Data', 3),\n"
                + "('TI9153', 'Komunikasi Bisnis', 3),\n"
                + "('TI9373', 'Manajemen Kepemimpinan', 3),\n"
                + "('TI9423', 'Manajemen Konten Web', 3),\n"
                + "('TI9433', 'Pemrograman Berorientasi Layanan', 3),\n"
                + "('TI9233', 'Praktikum Keahlian Khusus – SAP', 3),\n"
                + "('TI9473', 'Program Kreativitas Mahasiswa', 3),\n"
                + "('TI9483', 'Proyek Informatika Merdeka', 6),\n"
                + "('TI9253', 'Semantic Web', 3),\n"
                + "('TI9263', 'Sistem Informasi Geografis', 3),\n"
                + "('TI9273', 'Sistem Pakar', 3),\n"
                + "('TI9283', 'Technopreneurship dan Manajemen Inovasi', 3),\n"
                + "('TI9293', 'Teknik Animasi', 3),\n"
                + "('TI9443', 'Forensic Text', 3),\n"
                + "('TI9453', 'UX Writing dan Storytelling', 3),\n"
                + "('TI9333', 'Visualisasi Data', 3),\n"
                + "('TI9493', 'Augmented Reality', 3),\n"
                + "('TI9553', 'Pengenalan Aplikasi Berbasis AI', 3),\n"
                + "('TI9563', 'AI for Trading', 3),\n"
                + "('TI9573', 'Pemrograman Blockchain Dasar', 3),\n"
                + "('TI9583', 'Container Orchestration', 3),\n"
                + "('MH2023', 'Apresiasi Seni', 3),\n"
                + "('MH2033', 'Apresiasi Seni Musik', 3),\n"
                + "('MH2043', 'Pendidikan HAM dan Demokrasi', 3),\n"
                + "('MH2053', 'Pendidikan Perdamaian', 3),\n"
                + "('MH2083', 'Ilmu Sosial dan Budaya Dasar', 3),\n"
                + "('PB5033', 'English for Job Hunting', 3),\n"
                + "('PB5043', 'TOEFL Preparation', 3),\n"
                + "('PB5053', 'English for International Communication', 3),\n"
                + "('TI0323', 'Riset Teknologi Informasi', 3),\n"
                + "('TI0333', 'Kuliah Kerja Nyata', 3),\n"
                + "('TI0353', 'Kerja Praktik', 3),\n"
                + "('TI0366', 'Skripsi', 6);";

        String mahasiswa = "INSERT INTO mahasiswa (NIM, nama) VALUES\n"
                + "('71200001', 'Robert Downey Jr.'),\n"
                + "('71200002', 'Scarlett Johansson'),\n"
                + "('71200003', 'Cillian Murphy'),\n"
                + "('71200004', 'Zendaya'),\n"
                + "('71210005', 'Tom Holland'),\n"
                + "('71210006', 'Florence Pugh'),\n"
                + "('71210007', 'Leonardo DiCaprio'),\n"
                + "('71210008', 'Margot Robbie'),\n"
                + "('71220009', 'Keanu Reeves'),\n"
                + "('71220010', 'Emma Stone'),\n"
                + "('71220011', 'Ryan Gosling'),\n"
                + "('71220012', 'Natalie Portman'),\n"
                + "('71230013', 'Christian Bale'),\n"
                + "('71230014', 'Anya Taylor-Joy'),\n"
                + "('71230015', 'Benedict Cumberbatch'),\n"
                + "('71230016', 'Viola Davis'),\n"
                + "('71240017', 'Pedro Pascal'),\n"
                + "('71240018', 'Meryl Streep'),\n"
                + "('71240019', 'Denzel Washington'),\n"
                + "('71240020', 'Timothee Chalamet');";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs_matkul = stmt.executeQuery(checkMatkul);
            if (rs_matkul.next() && rs_matkul.getInt(1) == 0) {
                stmt.execute(matkul);
                System.out.println("Matakuliah seeded successfully.");
            }

            ResultSet rs_mahasiswa = stmt.executeQuery(checkMahasiswa);
            if (rs_mahasiswa.next() && rs_mahasiswa.getInt(1) == 0) {
                stmt.execute(mahasiswa);
                System.out.println("Mahasiswa seeded successfully.");
            }
            ResultSet rs_nilai = stmt.executeQuery(checkNilai);
            if (rs_nilai.next() && rs_nilai.getInt(1) == 0) {
                System.out.println("Seeding random grades for students...");

                // 1. Ambil semua kode_mk yang ada
                java.util.List<String> listKodeMk = new java.util.ArrayList<>();
                ResultSet allMk = stmt.executeQuery("SELECT kode_mk FROM matakuliah");
                while (allMk.next()) {
                    listKodeMk.add(allMk.getString("kode_mk"));
                }

                // 2. Ambil semua NIM mahasiswa yang ada
                java.util.List<String> listNim = new java.util.ArrayList<>();
                ResultSet allMhs = stmt.executeQuery("SELECT NIM FROM mahasiswa");
                while (allMhs.next()) {
                    listNim.add(allMhs.getString("NIM"));
                }

                // 3. Opsi Nilai
                String[] grades = {"A", "A-", "B+", "B", "B-", "C+", "C", "D", "E"};
                java.util.Random random = new java.util.Random();

                // 4. Proses Assign (Gunakan PreparedStatement agar lebih cepat dan aman)
                String sqlInsertNilai = "INSERT INTO nilai (nim, kode_mk, nilai) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertNilai)) {
                    for (String nim : listNim) {
                        // Acak urutan matkul untuk setiap mahasiswa
                        java.util.Collections.shuffle(listKodeMk);

                        // Ambil 40 matkul pertama hasil acak
                        for (int i = 0; i < 40 && i < listKodeMk.size(); i++) {
                            String randomGrade = grades[random.nextInt(grades.length)];

                            pstmt.setString(1, nim);
                            pstmt.setString(2, listKodeMk.get(i));
                            pstmt.setString(3, randomGrade);
                            pstmt.addBatch(); // Gunakan batch agar eksekusi cepat
                        }
                    }
                    pstmt.executeBatch();
                    System.out.println("Grades seeded successfully (40 subjects per student).");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error seeding data: " + e.getMessage());
        }
    }

    public Connection getConn(){
        return this.conn;
    }

    public static void main(String[] args) throws SQLException {
//        Database_manager db = new Database_manager("/home/nicsap/RPLBO/soal_ug_javafx/data.db");
        Database_manager db = new Database_manager();
    }
}
