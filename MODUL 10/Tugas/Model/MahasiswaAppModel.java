package id.ac.unpas.modul10.Tugas.Model;

import id.ac.unpas.modul10.KoneksiDB;
import java.sql.*;

public class MahasiswaAppModel {

    public ResultSet loadData() throws SQLException {
        Connection conn = KoneksiDB.configDB();
        Statement stm = conn.createStatement();
        return stm.executeQuery("SELECT * FROM mahasiswa");
    }

    public void tambahData(String nama, String nim, String jurusan) throws SQLException {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUE (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, nama);
        pst.setString(2, nim);
        pst.setString(3, jurusan);
        pst.execute();
    }

    public void ubahData(String nama, String jurusan, String nim) throws SQLException {
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim= ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, nama);
        pst.setString(2, jurusan);
        pst.setString(3, nim);
        pst.executeUpdate();
    }

    public void hapusData(String nim) throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, nim);
        pst.execute();
    }

    public ResultSet nyariData(String keyword) throws SQLException {
        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, "%" + keyword + "%");
        return pst.executeQuery();
    }

    public boolean ngecekNIM(String nim) throws SQLException {
        String sql = "SELECT nim FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, nim);
        ResultSet res = pst.executeQuery();
        return res.next();
    }
}
