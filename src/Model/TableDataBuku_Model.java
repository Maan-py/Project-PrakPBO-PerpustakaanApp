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
public class TableDataBuku_Model extends AbstractTableModel {
    List<DataBuku> db;
    
    public TableDataBuku_Model(List<DataBuku> db) {
        this.db = db;
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Judul";
            case 2:
                return "Genre";
            case 3:
                return "Tahun";
            case 4:
                return "Penulis";
            case 5:
                return "Link Cover";
            case 6:
                return "Status";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return db.get(row).getIdBuku();
            case 1:
                return db.get(row).getJudul();
            case 2:
                return db.get(row).getGenre();
            case 3:
                return db.get(row).getTahun();
            case 4:
                return db.get(row).getPenulis();
            case 5:
                return db.get(row).getLink_cover();
            case 6:
                return db.get(row).getStatus();
            default:
                return null;
        }
    }
}
