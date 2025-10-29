package id.ac.unpas.modul05;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
           public void run() {
               JFrame frame = new JFrame("Jendela dengan Label");
               frame.setSize(400, 300);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
               // 1. Atur Layout Manager
               // FlowLayout akan menyusun komponen dari kiri ke kanan.
               frame.setLayout(new FlowLayout());
               
               // 2. Buat Komponen GUI
               JLabel label = new JLabel("Teks Awal");
               JButton button = new JButton("Klik Saya!");
               
               // 3. Tambahkan Aksi (ActionListener) untuk tombol
               // penambahan ini menggunakan lambda untuk mempersingkat
               // penggunaan anonymous inner class
               button.addActionListener(e-> {
                  // Aksi ini akan menngubah teks pada label
                  label.setText("Tombol berhasil diklik!");
               });
               
               // 4. Tambahkan JLabel ke JFrame
               // Secara default, JFrame menggunakan BorderLayout
               // dan .add() akan menambahkannya ke bagian tengah (CENTER).
               frame.add(label);
               frame.add(button);
               
               frame.setVisible(true);
           } 
        });
    }
}