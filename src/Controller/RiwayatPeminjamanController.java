/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO_Data.DataRiwayatPeminjamanDAO;
import DAO_Implement.DataRiwayatPeminjamanImplement;
import Model.*;
import View.DashboardPage;
import View.RiwayatPeminjamanPage;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Maan
 */
public class RiwayatPeminjamanController {
    RiwayatPeminjamanPage riwayatPeminjamanPage;
    DashboardPage dashboardPage;
    DataRiwayatPeminjamanImplement implementRiwayat;
    List<DataRiwayatPeminjaman> drp;
    
    public RiwayatPeminjamanController(RiwayatPeminjamanPage riwayatPeminjamanPage) {
        this.riwayatPeminjamanPage = riwayatPeminjamanPage;
        implementRiwayat = new DataRiwayatPeminjamanDAO();
        drp = implementRiwayat.getAll(new DataRiwayatPeminjaman());
    }
    
    public RiwayatPeminjamanController(DashboardPage dashboardPage) {
        this.dashboardPage = dashboardPage;
        implementRiwayat = new DataRiwayatPeminjamanDAO();
        drp = implementRiwayat.getAll(new DataRiwayatPeminjaman());
    }
   
    public void insert() {
//        DataRiwayatPeminjaman db = new DataRiwayatPeminjaman();

//        if (riwayatPeminjamanPage.getjJudulField().getText().isEmpty() || manajemenBukuPage.getjGenreField().getText().isEmpty() || manajemenBukuPage.getjTahunField().getText().isEmpty() || manajemenBukuPage.getjPenulisField().getText().isEmpty() || manajemenBukuPage.getjLink_CoverField().getText().isEmpty()) {
//            JOptionPane.showMessageDialog(riwayatPeminjamanPage, "Semua input data harus diisi", "Kesalahan", JOptionPane.WARNING_MESSAGE);
//            return;
//        }

        int baris = dashboardPage.getjTableBuku().getSelectedRow();

        if (baris != -1) {
            int idBuku = Integer.parseInt(dashboardPage.getjTableBuku().getValueAt(baris, 0).toString());
            int idUser = DataUsers.currentUser.getIdUser();
            DataRiwayatPeminjaman riwayat = drp.get(baris);
            riwayat.setIdBuku(idBuku);
            riwayat.setIdPeminjam(idUser);
            riwayat.setTanggalPeminjaman(LocalDate.now().toString());

            implementRiwayat.insert(riwayat);

            JOptionPane.showMessageDialog(riwayatPeminjamanPage, "Buku berhasil dipinjam", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(riwayatPeminjamanPage, "Pilih buku yang ingin dipinjam", "Kesalahan", JOptionPane.WARNING_MESSAGE);
    }
    
    public void isiTableRiwayatPeminjamanPage() {
        drp = implementRiwayat.getAll(new DataRiwayatPeminjaman());

        TableDataRiwayatPeminjaman_Model mrp = new TableDataRiwayatPeminjaman_Model(drp);
        riwayatPeminjamanPage.getjTableBuku().setModel(mrp);
    }
    
    public void isiTableDashboardPage() {
        drp = implementRiwayat.getAll(new DataRiwayatPeminjaman());

        TableDataRiwayatPeminjaman_Model mrp = new TableDataRiwayatPeminjaman_Model(drp);
        dashboardPage.getjTableBuku().setModel(mrp);
    }
   
}
