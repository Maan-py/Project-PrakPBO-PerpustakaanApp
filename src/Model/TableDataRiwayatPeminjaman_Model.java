/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maan
 */
public class TableDataRiwayatPeminjaman_Model extends AbstractTableModel {
    
    List<DataRiwayatPeminjaman> db;

    public TableDataRiwayatPeminjaman_Model(List<DataRiwayatPeminjaman> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID Peminjaman";
            case 1:
                return "Nama Buku";
            case 2:
                return "Nama Peminjam";
            case 3:
                return "Tanggal Peminjaman";
            case 4:
                return "Status Buku";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return db.get(row).getIdPeminjaman();
            case 1:
                return db.get(row).getNamaBuku();
            case 2:
                return db.get(row).getNamaPeminjam();
            case 3:
                return db.get(row).getTanggalPeminjaman();
            case 4:
                return db.get(row).getStatusBuku();
            default:
                return null;
        }
    }
}
