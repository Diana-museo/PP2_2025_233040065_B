package id.ac.unpas.modul06;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ContohGridLayout {
    public static void main(String[] args) {
        // 1. Buat frame
        JFrame frame = new JFrame("Contoh GridLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        
        // 2. Atur layout frame menjadi GridLayout 3 baris, 2 kolom
        // Kita juga bisa menambahkan jarak (gap) antar sel
        frame.setLayout(new GridLayout(3, 2, 5, 5));
        
        // 3. Tambahkan 6 komponen (3 * 2 = 6)
        frame.add(new JLabel("Label 1:"));
        frame.add(new JTextField());
        frame.add(new JLabel("Label 2:"));
        frame.add(new JPasswordField());
        frame.add(new JButton("Login"));
        frame.add(new JButton("Batal"));
        
        // 4.Tampilkan frame
        frame.setVisible(true);
    }
}
