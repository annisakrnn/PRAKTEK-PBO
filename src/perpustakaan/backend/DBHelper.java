package perpustakaan.backend;

import java.sql.*;public class DBHelper {
    private static Connection koneksi;

    // membuka koneksi ke database
    public static void bukaKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/dbperpus?useSSL=false&serverTimezone=UTC";
                String user = "root";
                String password = "";

                // menggunakan driver baru
                Class.forName("com.mysql.cj.jdbc.Driver");
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil!");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Error koneksi: " + e.getMessage());
            }
        }
    }

    // menjalankan query INSERT dan mendapatkan ID yang dihasilkan
    public static int insertQueryGetID(String query) {
        bukaKoneksi();
        int result = -1;

        try (Statement stmt = koneksi.createStatement()) {
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // menjalankan query (INSERT, UPDATE, DELETE)
    public static boolean executeQuery(String query) {
        bukaKoneksi();
        boolean result = false;

        try (Statement stmt = koneksi.createStatement()) {
            stmt.executeUpdate(query);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // menjalankan query SELECT dan mengembalikan ResultSet
    public static ResultSet selectQuery(String query) {
        bukaKoneksi();
        ResultSet rs = null;

        try {
            Statement stmt = koneksi.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    static Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

   
}
