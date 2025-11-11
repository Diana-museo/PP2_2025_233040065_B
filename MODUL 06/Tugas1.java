package id.ac.unpas.modul06;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tugas1 {
    public static void main(String[] args) {
        // 1. Membuat frame dan Mengatur layout memakai BorderLayout
        JFrame frame = new JFrame("Kalkulator Sederhana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        // 2. Membuat textfield sebagai display perhitungan
        //    Isi teks rata kanan, tidak bisa diketik manual jadi harus via tombol
        JTextField displayHitung = new JTextField();
        displayHitung.setHorizontalAlignment(JTextField.RIGHT);
        displayHitung.setEditable(false);
        displayHitung.setPreferredSize(new Dimension(300, 70));
        displayHitung.setFont(new Font("", Font.BOLD, 24));
        frame.add(displayHitung, BorderLayout.NORTH);

        // 3. Membuat panel untuk mewadahi tombol-tombol kalkulator
        //    Menggunakan GridLayout 4 baris, 4 kolom serta gap antar sel
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new GridLayout(4, 4, 5, 5));

        // 4. Membuat warna tombol jadi hitam
        Color warnaTombol = Color.BLACK;

        // Membuat objek tombol 0-9, /, *, -, +, =, C (Clear)
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btnBagi = new JButton("/");

        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btnKali = new JButton("*");

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btnKurang = new JButton("-");

        JButton btnC = new JButton("C");
        JButton btn0 = new JButton("0");
        JButton btnSama = new JButton("=");
        JButton btnTambah = new JButton("+");

        // Memberi warna tombol yang sudah diset hitam.
        btn7.setBackground(warnaTombol);
        btn8.setBackground(warnaTombol);
        btn9.setBackground(warnaTombol);
        btnBagi.setBackground(warnaTombol);

        btn4.setBackground(warnaTombol);
        btn5.setBackground(warnaTombol);
        btn6.setBackground(warnaTombol);
        btnKali.setBackground(warnaTombol);

        btn1.setBackground(warnaTombol);
        btn2.setBackground(warnaTombol);
        btn3.setBackground(warnaTombol);
        btnKurang.setBackground(warnaTombol);

        btnC.setBackground(warnaTombol);
        btn0.setBackground(warnaTombol);
        btnSama.setBackground(warnaTombol);
        btnTambah.setBackground(warnaTombol);

        // 5. Membuat teks jadi warna putih 
        Color warnaTeks = Color.WHITE;
        btn7.setForeground(warnaTeks);
        btn8.setForeground(warnaTeks);
        btn9.setForeground(warnaTeks);
        btnBagi.setForeground(warnaTeks);

        btn4.setForeground(warnaTeks);
        btn5.setForeground(warnaTeks);
        btn6.setForeground(warnaTeks);
        btnKali.setForeground(warnaTeks);

        btn1.setForeground(warnaTeks);
        btn2.setForeground(warnaTeks);
        btn3.setForeground(warnaTeks);
        btnKurang.setForeground(warnaTeks);

        btnC.setForeground(warnaTeks);
        btn0.setForeground(warnaTeks);
        btnSama.setForeground(warnaTeks);
        btnTambah.setForeground(warnaTeks);

        // Menambahkan semua tombol ke dalam panel tombol sesuai urutan
        panelTombol.add(btn7);
        panelTombol.add(btn8);
        panelTombol.add(btn9);
        panelTombol.add(btnBagi);

        panelTombol.add(btn4);
        panelTombol.add(btn5);
        panelTombol.add(btn6);
        panelTombol.add(btnKali);

        panelTombol.add(btn1);
        panelTombol.add(btn2);
        panelTombol.add(btn3);
        panelTombol.add(btnKurang);

        panelTombol.add(btnC);
        panelTombol.add(btn0);
        panelTombol.add(btnSama);
        panelTombol.add(btnTambah);

        // 6. Mendefinisikan variabel untuk menampung nilai dan operator
        //    Agar kalkulator bisa dioperasikan
        final double[] nilai1 = new double[1];
        final String[] operator = new String[1];
        // Membuat aksi saat tombol angka ditekan
        ActionListener klikAngka = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sumber = (JButton) e.getSource();
                displayHitung.setText(displayHitung.getText() + sumber.getText());
            }
        };

        // Menambahkan aksi klikAngka untuk semua tombol angka
        btn0.addActionListener(klikAngka);
        btn1.addActionListener(klikAngka);
        btn2.addActionListener(klikAngka);
        btn3.addActionListener(klikAngka);
        btn4.addActionListener(klikAngka);
        btn5.addActionListener(klikAngka);
        btn6.addActionListener(klikAngka);
        btn7.addActionListener(klikAngka);
        btn8.addActionListener(klikAngka);
        btn9.addActionListener(klikAngka);

        // Membuat aksi saat tombol angka ditekan
        ActionListener klikOperator = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sumber = (JButton) e.getSource();
                operator[0] = sumber.getText();
                nilai1[0] = Double.parseDouble(displayHitung.getText());
                displayHitung.setText(""); // kosongkan untuk nilai berikutnya
            }
        };

        // Menambahkan aksi klikOperator untuk semua tombol operator
        btnTambah.addActionListener(klikOperator);
        btnKurang.addActionListener(klikOperator);
        btnKali.addActionListener(klikOperator);
        btnBagi.addActionListener(klikOperator);

        // 8. Membuat aksi saat tombol (=) ditekan
        //    Agar dapat menghitung sesuai nilai dan operator yang dimasukkan
        btnSama.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double nilai2 = Double.parseDouble(displayHitung.getText());
                double hasil = 0;

                if (operator[0].equals("+")) {
                    hasil = nilai1[0] + nilai2;
                } else if (operator[0].equals("-")) {
                    hasil = nilai1[0] - nilai2;
                } else if (operator[0].equals("*")) {
                    hasil = nilai1[0] * nilai2;
                } else if (operator[0].equals("/")) {
                    hasil = nilai1[0] / nilai2;
                }

                // biar gak ada .0 kalau hasilnya bulat
                // biar kaya kalkulator beneran
                if (hasil == (int) hasil) {
                    displayHitung.setText(String.valueOf((int) hasil));
                } else {
                    displayHitung.setText(String.valueOf(hasil));
                }
            }
        });

        // 9. Membuat aksi saat tombol C (Clear) ditekan
        //    untuk menghapus dan mereset operasi yang dilakukan
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHitung.setText("");
                nilai1[0] = 0;
                operator[0] = "";
            }
        });

        // 10. Menambahkan panel tombol ke frame di posisi tengah
        frame.add(panelTombol, BorderLayout.CENTER);

        // Menampilkan frame ke layar
        frame.setVisible(true);
    }
}