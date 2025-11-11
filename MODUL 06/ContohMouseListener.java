package id.ac.unpas.modul06;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ContohMouseListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Contoh MouseListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        
        // 1. Buat komponen (Event source dan komponen lain)
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 200));
        
        // 2. Buat Event Listener (menggunakan MouseAdapter)
        MouseAdapter adapter = new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                //Saat mouse masuk, ubah warna jadi biru
                panel.setBackground(Color.CYAN);
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                // Saat mouse keluar, kembalikan warna
                panel.setBackground(Color.LIGHT_GRAY);
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                // Saat diklik, tampilkan koordinat klik
                System.out.println("Mouse diklik di: x= " + e.getX() + ", y= " + e.getY());
            }
        };
        
        // 4. Daftarkan listener ke source
        panel.addMouseListener(adapter);
        
        // Tambahkan komponen ke frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
