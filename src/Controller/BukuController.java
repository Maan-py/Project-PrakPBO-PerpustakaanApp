/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO_Data.DataBukuDAO;
import DAO_Implement.DataBukuImplement;
import Model.DataBuku;
import Model.TableDataBuku_Model;
import View.DashboardPage;
import View.ManajemenBukuPage;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Maan
 */
public class BukuController {
    ManajemenBukuPage manajemenBukuPage;
    DashboardPage dashboardPage;
    DataBukuImplement implementBuku;
    List<DataBuku> db;

    public BukuController(ManajemenBukuPage manajemenBukuPage) {
        this.manajemenBukuPage = manajemenBukuPage;
        implementBuku = new DataBukuDAO();
        db = implementBuku.getAll(new DataBuku());
    }

    public BukuController(DashboardPage dashboardPage) {
        this.dashboardPage = dashboardPage;
        implementBuku = new DataBukuDAO();
        db = implementBuku.getAll(new DataBuku());
    }

    public void insert() {
        DataBuku db = new DataBuku();

        if (manajemenBukuPage.getjJudulField().getText().isEmpty() || manajemenBukuPage.getjGenreField().getText().isEmpty() || manajemenBukuPage.getjTahunField().getText().isEmpty() || manajemenBukuPage.getjPenulisField().getText().isEmpty() || manajemenBukuPage.getjLink_CoverField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(manajemenBukuPage, "Semua input data harus diisi", "Kesalahan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        db.setJudul(manajemenBukuPage.getjJudulField().getText());
        db.setGenre(manajemenBukuPage.getjGenreField().getText());
        db.setTahun(manajemenBukuPage.getjTahunField().getText());
        db.setPenulis(manajemenBukuPage.getjPenulisField().getText());
        db.setLink_cover(manajemenBukuPage.getjLink_CoverField().getText());
        db.setStatus(manajemenBukuPage.getjComboBoxStatus().getSelectedItem().toString());

        implementBuku.insert(db);
    }

    public void isiTabelManajemenBukuPage() {
        db = implementBuku.getAll(new DataBuku());

        TableDataBuku_Model mb = new TableDataBuku_Model(db);
        manajemenBukuPage.getjTableBuku().setModel(mb);
    }

    public void isiTabelDashboardPage() {
        db = implementBuku.getAll(new DataBuku());

        TableDataBuku_Model mb = new TableDataBuku_Model(db);
        dashboardPage.getjTableBuku().setModel(mb);
    }

    public void update() {
        int baris = manajemenBukuPage.getjTableBuku().getSelectedRow();

        if (baris != -1) {
            DataBuku buku = db.get(baris);
            buku.setIdBuku(buku.getIdBuku());
            buku.setJudul(manajemenBukuPage.getjJudulField().getText());
            buku.setGenre(manajemenBukuPage.getjGenreField().getText());
            buku.setTahun(manajemenBukuPage.getjTahunField().getText());
            buku.setPenulis(manajemenBukuPage.getjPenulisField().getText());
            buku.setLink_cover(manajemenBukuPage.getjLink_CoverField().getText());
            buku.setStatus(manajemenBukuPage.getjComboBoxStatus().getSelectedItem().toString());

            implementBuku.update(buku);

            manajemenBukuPage.getjJudulField().setText("");
            manajemenBukuPage.getjGenreField().setText("");
            manajemenBukuPage.getjTahunField().setText("");
            manajemenBukuPage.getjPenulisField().setText("");
            manajemenBukuPage.getjLink_CoverField().setText("");
            manajemenBukuPage.getjComboBoxStatus().setSelectedItem("Tersedia");

            JOptionPane.showMessageDialog(manajemenBukuPage, "Buku berhasil diupdate", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(manajemenBukuPage, "Pilih data yang ingin diupdate", "Kesalahan", JOptionPane.WARNING_MESSAGE);
    }

    public void delete() {
        int baris = manajemenBukuPage.getjTableBuku().getSelectedRow();

        manajemenBukuPage.getjJudulField().setText("");
        manajemenBukuPage.getjGenreField().setText("");
        manajemenBukuPage.getjTahunField().setText("");
        manajemenBukuPage.getjPenulisField().setText("");
        manajemenBukuPage.getjLink_CoverField().setText("");
        manajemenBukuPage.getjComboBoxStatus().setSelectedItem("Tersedia");

        if (baris != -1) {
            DataBuku buku = db.get(baris);
            int id = buku.getIdBuku();

            int konfirmasi = JOptionPane.showConfirmDialog(manajemenBukuPage, "Apakah anda yakin ingin menghapus buku berjudul " + manajemenBukuPage.getjJudulField().getText() + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

            if (konfirmasi == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(manajemenBukuPage, "Buku berhasil dihapus", "Berhasil", JOptionPane.INFORMATION_MESSAGE);

                implementBuku.delete(id);

                return;
            }

            manajemenBukuPage.getjJudulField().setText("");
            manajemenBukuPage.getjGenreField().setText("");
            manajemenBukuPage.getjTahunField().setText("");
            manajemenBukuPage.getjPenulisField().setText("");
            manajemenBukuPage.getjLink_CoverField().setText("");
            manajemenBukuPage.getjComboBoxStatus().setSelectedItem("Tersedia");

            return;
        }

        JOptionPane.showMessageDialog(manajemenBukuPage, "Pilih data yang ingin dihapus", "Kesalahan", JOptionPane.WARNING_MESSAGE);
    }
}
