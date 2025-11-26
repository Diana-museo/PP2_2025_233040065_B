package id.ac.unpas.modul07;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TugasModul7 extends JFrame {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;
    
    // Method untuk membuat desain Tab Input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Komponen Nama
        panel.add(new JLabel("Nama Siswa: "));
        txtNama = new JTextField();
        panel.add(txtNama);
        
        // Komponen Mata Pelajaran (ComboBox)
        panel.add(new JLabel("Mata Pelajaran: "));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
            "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);
        
        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100): "));
        txtNilai = new JTextField();
        panel.add(txtNilai);
        
        // Tombol Reset
        JButton btnReset = new JButton("Reset");
        panel.add(btnReset);
        
        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(btnSimpan);
        
        // Event Handling Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesReset();
            }
        });
        
        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        
        return panel;
    }
    
    // Method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Model Tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);
        
        // Selection untuk baris data
        tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Membungkus tabel dengan ScrollPane (agar bisa discroll jika data banyak)
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Tombol Hapus
        JPanel panelBagianBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnHapus = new JButton("Hapus Baris");
        panelBagianBawah.add(btnHapus);
        panel.add(panelBagianBawah, BorderLayout.SOUTH);

        // Event Handling Tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesHapus();
            }
        });
        return panel;
    }
    
    // Logika Validasi dan Hapus Data
    private void prosesHapus() {
        int row = tableData.getSelectedRow();

        // 1. Pengecekan baris terpilih
        // Validasi 1 : Cek apakah ada baris yang dipilih
        if (row < 0) {
            JOptionPane.showMessageDialog(
                this,
                "Pilih baris yang ingin dihapus terlebih dahulu!",
                "Tidak Ada Baris Dipilih",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 2. Munculkan konfirmasi
        int konfirmasi = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin menghapus baris data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // 3. Jika klik "YES", hapus baris
        if (konfirmasi == JOptionPane.YES_OPTION) {
            tableModel.removeRow(row);
        }
    }
    
    // Logika Reset Input
    private void prosesReset() {
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
    }

    // Logika Validasi dan Penyimpanan Data
    private void prosesSimpan() {
        // 1. Ambil data dari input
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();
        
        // 2. Validasi input nama
        // Validasi 1a : Cek apakah nama kosong
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validasi 1b : Nama harus minimal 3 karakter
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nama harus terdiri dari minimal 3 karakter!",
                    "Error Validasi",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        // Validasi 1c : Nama tidak boleh ada simbol dan huruf
        if (!nama.matches("[\\p{L} ]+")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nama hanya boleh berisi huruf dan spasi!",
                    "Error Validasi",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Validasi 2 : Cek apakah nilai berupa angka dan dalam range valid
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if(nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0-100!",
                        "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 3. Logika Bisnis (Menentukan Grade)
        int nilainya = nilai / 10;
        String grade;
        switch (nilainya) {
            case 10:
            case 9:
            case 8:
                grade = "A";
                break;
            case 7:
                grade = "AB";
                break;
            case 6:
                grade = "B";
                break;
            case 5:
                grade = "BC";
                break;
            case 4:
                grade = "C";
                break;
            case 3:
                grade = "D";
                break;
            default:
                grade = "E";
                break;
        }
        
        // 4. Masukkan ke Tabel (Update Model)
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);
        
        // 5. Reset Form dan Pindah Tab
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
        
        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1); // Otomatis pindah ke tab tabel
    }

    public TugasModul7() {
        // 1. Konfigurasi Frame Utama
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi di tengah layar
        
        // 2. Inisialisasi Tabbed Pane
        tabbedPane = new JTabbedPane();
        
        // 3. Mmebuat panel untuk tab 1 (form input)
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);
        
        // 4. Membuat Panel untuk tab 2 (tabel data)
        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel);
        
        // Menambahkan TabbedPane ke Frame
        add(tabbedPane);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TugasModul7().setVisible(true);
        });
    }
}
