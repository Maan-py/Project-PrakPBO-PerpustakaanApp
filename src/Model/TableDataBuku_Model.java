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
        return 6;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Judul";
            case 1:
                return "Genre";
            case 2:
                return "Tahun";
            case 3:
                return "Penulis";
            case 4:
                return "Link Cover";
            case 5:
                return "Status";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return db.get(row).getJudul();
            case 1:
                return db.get(row).getGenre();
            case 2:
                return db.get(row).getTahun();
            case 3:
                return db.get(row).getPenulis();
            case 4:
                return db.get(row).getLink_cover();
            case 5:
                return db.get(row).getStatus();
            default:
                return null;
        }
    }
}
