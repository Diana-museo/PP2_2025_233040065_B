package id.ac.unpas.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Tugas1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
           public void run() {
               JFrame frame = new JFrame("Jendela dengan Label");
               frame.setSize(400, 300);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
               frame.setLayout(new BorderLayout());

               JLabel label = new JLabel("Label ada di Atas (NORTH)");
               JButton button = new JButton("Tombol ada di Bawah (SOUTH)");
               JButton buttonWest = new JButton("WEST");
               JButton buttonEast = new JButton("EAST");
               JButton buttonCenter = new JButton("CENTER");
                
               
               button.addActionListener(e-> {
                  label.setText("Tombol di SOUTH diklik!");
               });
               buttonWest.addActionListener(e-> {
                  label.setText("Tombol di WEST diklik!");
               });
               buttonEast.addActionListener(e-> {
                  label.setText("Tombol di EAST diklik!");
               });
               buttonCenter.addActionListener(e-> {
                  label.setText("Tombol di CENTER diklik!");
               });
               
               frame.add(label, BorderLayout.NORTH);
               frame.add(button, BorderLayout.SOUTH);
               frame.add(buttonWest, BorderLayout.WEST);
               frame.add(buttonEast, BorderLayout.EAST);
               frame.add(buttonCenter, BorderLayout.CENTER);
               
               frame.setVisible(true);
           } 
        });
    }
}