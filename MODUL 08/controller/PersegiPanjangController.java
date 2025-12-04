package id.ac.unpas.modul08.controller;

import id.ac.unpas.modul08.model.PersegiPanjangModel;
import id.ac.unpas.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    // Model dan view sebagai atribut kelas
    private PersegiPanjangModel model;
    private PersegiPanjangView view;
    
    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        
        // Menghubungkan tombol di View dengan logic di Controller
        this.view.addHitungListener(new HitungListener());
        this.view.addResetListener(new ResetListener());
    }
    
    // Inner class untuk menangani event klik tombol
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil data dari view
                double p = view.getPanjang();
                double l = view.getLebar();
                
                // 2. Kirim data ke model
                model.setPanjang(p);
                model.setLebar(l);
            
                // 3. Jalankan logika bisnis di model
                model.hitungLuas();
                model.hitungKeliling();
                
                // 4. Ambil hasil dari model dan tampilkan kembali di view
                double hasilLuas = model.getLuas();
                view.setHasilLuas(hasilLuas);
                double hasilKeliling = model.getKeliling();
                view.setHasilKeliling(hasilKeliling);
                
            } catch(NumberFormatException ex) {
                // Handle jika user memasukkan huruf
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
            
        }
    }
    
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetInput();
        }
    }
}
