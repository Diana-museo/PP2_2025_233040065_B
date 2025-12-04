package id.ac.unpas.modul08.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-");
    private JLabel lblHasilKeliling = new JLabel("-");
    private JButton btnReset = new JButton("Reset Input");
    private JButton btnHitung = new JButton("Hitung Luas");
    
    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLayout(new GridLayout(5, 2, 10, 10));
        this.setTitle("MVC Kalkulator");
        
        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasilLuas);
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblHasilKeliling);
        //this.add(new JLabel(""));
        this.add(btnReset);
        //this.add(new JLabel(""));
        this.add(btnHitung);
    }
    
    // Fungsi Reset
    public void resetInput() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-");
    }

    
    // Mengambil nilai Panjang dari TextField
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    // Mengambil nilai Panjang dari TextField
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }
    
    // Menampilkan hasil ke Label
    public void setHasilLuas(double hasilLuas) {
        lblHasilLuas.setText(String.valueOf(hasilLuas));
    }
    
    // Menampilkan hasil ke Label
    public void setHasilKeliling (double hasilKeliling) {
        lblHasilKeliling.setText(String.valueOf(hasilKeliling));
    }
    
    // Menampilkan pesan error (Jika input bukan angka)
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }
    
    // Mendaftarkan Listener untuk tombol (Controller yang akan memberikan aksinya)
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }
    public void addResetListener(ActionListener resetlistener) {
        btnReset.addActionListener(resetlistener);
    }
}
