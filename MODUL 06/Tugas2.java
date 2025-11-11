package id.ac.unpas.modul06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tugas2 {
    public static void main(String[] args) {
        // 1. Membuat frame
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());
        
        // 2. Membuat label dan textfield untuk input celcius
        JLabel Celcius = new JLabel("Celcius: ");
        JTextField txtCelcius = new JTextField(10);
        
        // 3. Membuat tombol konversi
        JButton btnKonversi = new JButton("Konversi");
        
        // 4. Membuat label hasil
        JLabel Fahrenheit = new JLabel("Fahrenheit: ");
        JLabel Hasil = new JLabel("");
        
        // 5. Menambahkan komponen ke frame
        frame.add(Celcius);
        frame.add(txtCelcius);
        frame.add(btnKonversi);
        frame.add(Fahrenheit);
        frame.add(Hasil);
        
        // 6. Menambahkan aksi tombol konversi
        btnKonversi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double celcius = Double.parseDouble(txtCelcius.getText());
                    double fahrenheit = (celcius * 9 / 5) + 32;
                    Hasil.setText(String.format("%.2f", fahrenheit));
                } catch(NumberFormatException ex) {
                    Hasil.setText("Input tidak valid");
                }
            }
        });
        
        // 7. Menampilkan frame ke layar
        frame.setVisible(true);
    }
}
