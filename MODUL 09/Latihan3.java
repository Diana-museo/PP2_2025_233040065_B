package id.ac.unpas.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Latihan3 extends JFrame {
    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;
    
    // Button baru UserConfig
    private JButton btnSaveUser, btnLoadUser;
    
    public Latihan3() {
        super("Tutorial File IO & Exception Handling");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();
        
        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");
        
        // Panel Tombol baru UserConfig
        btnSaveUser = new JButton("Simpan UserConfig");
        btnLoadUser = new JButton("Muat UserConfig");
        
        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        buttonPanel.add(btnSaveUser);
        buttonPanel.add(btnLoadUser);
        
        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // --- Event Handling ---
        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());
        
        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());
        
        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        
        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
        
        // 5. MENYIMPAN OBJECT JAVA (Object Stream / Serialization)
        btnSaveUser.addActionListener(e -> simpanUserConfig());
        
        // 6. MEMBACA OBJECT JAVA (Object Stream / Deserialization)
        btnLoadUser.addActionListener(e -> muatUserConfig());

    }

    // Contoh: Membaca file teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally
            
            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area
                
                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                
                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");
            } catch(FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch(IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close();
                    } 
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    // Contoh: Menulis file teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Try-with-resources otomatis menutup stream tanpa blok finally manual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch(IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        } 
    }
    
    // Contoh: Menulis binary (Menyimpan ukuran font saat ini ke file .bin)
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            // Kita simpan ukuran font saat ini (Integer)
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            
            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ")");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }
    
    // Contoh: Membaca binary (Mengambil ukuran font dari file .bin)
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            // Membaca data Integer mentah
            int fontSize = dis.readInt();
            
            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }
    
    // Contoh: Menyimpan sebuah OBJECT Java menggunakan ObjectOutputStream
    private void simpanUserConfig() {
        UserConfig config = new UserConfig();

        // Ambil username dari TextArea baris pertama (contoh)
        String username = textArea.getText().split("\n").length > 0
                ? textArea.getText().split("\n")[0]
                : "unknown";

        config.setUsername(username);
        config.setFontSize(textArea.getFont().getSize());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_config.dat"))) {
            oos.writeObject(config);
            JOptionPane.showMessageDialog(this, "UserConfig berhasil disimpan!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan UserConfig: " + e.getMessage());
        }
    }

    // Contoh: Membaca kembali OBJECT Java menggunakan ObjectInputStream
    private void muatUserConfig() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_config.dat"))) {

            Object obj = ois.readObject();

            if (obj instanceof UserConfig) {
                UserConfig config = (UserConfig) obj;

                // Terapkan ke UI
                textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontSize()));
                textArea.setText("Username: " + config.getUsername() + "\n");

                JOptionPane.showMessageDialog(this,
                    "UserConfig dimuat!\nUsername: " + config.getUsername() +
                    "\nFont Size: " + config.getFontSize());
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File user_config.dat belum ada!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat UserConfig: " + e.getMessage());
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Latihan3().setVisible(true);
        });
    }
}
