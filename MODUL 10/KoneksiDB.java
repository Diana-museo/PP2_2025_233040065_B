package id.ac.unpas.modul10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {
    private static Connection mysqlconfig;
    
    public static Connection configDB() throws SQLException {
        try {
            // URL Database (Ganti 'root' dan '' sesuai user/pass database lokal anda)
            String url = "jdbc:mysql://localhost:3306/kampus_db";
            String user = "root";
            String pass = "";
            
            // Registrasi Driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            // Buat Koneksi
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
        return mysqlconfig;
    }
}
