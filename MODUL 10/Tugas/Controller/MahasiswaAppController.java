package id.ac.unpas.modul10.Tugas.Controller;

import id.ac.unpas.modul10.Tugas.Model.MahasiswaAppModel;
import id.ac.unpas.modul10.Tugas.View.MahasiswaAppView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class MahasiswaAppController {

    private MahasiswaAppView view;
    private MahasiswaAppModel model;

    public MahasiswaAppController(MahasiswaAppView view, MahasiswaAppModel model) {
        this.view = view;
        this.model = model;

        // --- Event Listeners ---
        view.btnSimpan.addActionListener(e -> tambahData());
        view.btnEdit.addActionListener(e -> ubahData());
        view.btnHapus.addActionListener(e -> hapusData());
        view.btnClear.addActionListener(e -> kosongkanForm());
        view.btnSearch.addActionListener(e -> nyariData());

        view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tableMahasiswa.getSelectedRow();
                view.txtNama.setText(view.model.getValueAt(row, 1).toString());
                view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
                view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
            }
        });

        loadData();
    }

    // --- LOGIKA CRUDS ---
    private void loadData() {
        view.model.setRowCount(0);
        try {
            ResultSet res = model.loadData();
            int no = 1;
            while (res.next()) {
                view.model.addRow(new Object[]{
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Load Data: " + e.getMessage());
        }
    }

    private void tambahData() {
        // Validasi Pengisian
        if (view.txtNama.getText().trim().isEmpty() ||
            view.txtNIM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!");
            return;
        }

        // Validasi NIM
        try {
            if (model.ngecekNIM(view.txtNIM.getText())) {
                JOptionPane.showMessageDialog(null, "NIM sudah terdaftar!");
                return;
            }

            model.tambahData(
                    view.txtNama.getText(),
                    view.txtNIM.getText(),
                    view.txtJurusan.getText()
            );

            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void ubahData() {
        try {
            model.ubahData(
                    view.txtNama.getText(),
                    view.txtJurusan.getText(),
                    view.txtNIM.getText()
            );

            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Edit: " + e.getMessage());
        }
    }

    private void hapusData() {
        try {
            model.hapusData(view.txtNIM.getText());

            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Hapus: " + e.getMessage());
        }
    }

    private void nyariData() {
        view.model.setRowCount(0);
        try {
            ResultSet res = model.nyariData(view.txtSearch.getText());
            int no = 1;
            while (res.next()) {
                view.model.addRow(new Object[]{
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Cari Data: " + e.getMessage());
        }
    }

    private void kosongkanForm() {
        view.txtNama.setText(null);
        view.txtNIM.setText(null);
        view.txtJurusan.setText(null);
    }
}
