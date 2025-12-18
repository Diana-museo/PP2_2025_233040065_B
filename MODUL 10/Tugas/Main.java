package id.ac.unpas.modul10.Tugas;

import id.ac.unpas.modul10.Tugas.View.MahasiswaAppView;
import id.ac.unpas.modul10.Tugas.Model.MahasiswaAppModel;
import id.ac.unpas.modul10.Tugas.Controller.MahasiswaAppController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaAppView view = new MahasiswaAppView();
            MahasiswaAppModel model = new MahasiswaAppModel();
            new MahasiswaAppController(view, model);
            view.setVisible(true);
        });
    }
}
