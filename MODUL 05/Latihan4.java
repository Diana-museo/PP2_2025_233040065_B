package id.ac.unpas.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
           public void run() {
               JFrame frame = new JFrame("Jendela dengan Label");
               frame.setSize(400, 300);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
               // 1. Atur Layout Manager
               // Sebenarnya ini tidak perlu
               // Karena BorderLayout adalah Layout Manager default
               frame.setLayout(new BorderLayout());
               
               // 2. Buat Komponen GUI
               JLabel label = new JLabel("Label ada di Atas (NORTH)");
               JButton button = new JButton("Tombol ada di Bawah (SOUTH)");
               
               // 3. Tambahkan Aksi (ActionListener) untuk tombol
               button.addActionListener(e-> {
                  // Aksi ini akan menngubah teks pada label
                  label.setText("Tombol di SOUTH diklik!");
               });
               
               // 4. Tambahkan komponen ke frame DENGAN POSISI
               frame.add(label, BorderLayout.NORTH);
               frame.add(button, BorderLayout.SOUTH);
               
               // Kita bisa tambahkan komponen lain
               frame.add(new JButton("WEST"), BorderLayout.WEST);
               frame.add(new JButton("EAST"), BorderLayout.EAST);
               frame.add(new JButton("CENTER"), BorderLayout.CENTER);
               
               frame.setVisible(true);
           } 
        });
    }
}