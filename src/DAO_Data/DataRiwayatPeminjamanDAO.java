/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_Data;

import DAO_Implement.DataRiwayatPeminjamanImplement;
import Model.DataRiwayatPeminjaman;
import java.sql.*;
import Connection.Connector;
import Model.DataBuku;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maan
 */
public class DataRiwayatPeminjamanDAO implements DataRiwayatPeminjamanImplement {
    Connection connection;
    String insert = "INSERT INTO riwayat_peminjaman (id_peminjaman, id_buku, id_user, tanggal_peminjaman) VALUES(NULL, ?, ?, ?)";
    String select = "SELECT rp.id_peminjaman, b.judul AS judul_buku, b.status AS status_buku, u.username AS nama_peminjam, rp.tanggal_peminjaman AS tanggal_peminjaman FROM riwayat_peminjaman rp JOIN buku b ON rp.id_buku = b.id JOIN users u ON rp.id_user = u.id ORDER BY id_peminjaman ASC";
    String updateStatusBuku = "UPDATE buku SET status = 'Dipinjam' WHERE id = ?";
    public DataRiwayatPeminjamanDAO() {
        connection = Connector.connection();
    }

    @Override
    public void insert(DataRiwayatPeminjaman rp) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, rp.getIdBuku());
            statement.setInt(2, rp.getIdPeminjam());
            statement.setString(3, rp.getTanggalPeminjaman());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            while (rs.next()) {
                rp.setIdPeminjaman(rs.getInt(1));
            }

            updateStatusBuku(rp.getIdBuku());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<DataRiwayatPeminjaman> getAll(DataRiwayatPeminjaman rp) {
        List<DataRiwayatPeminjaman> drp = null;
        
        try {
            drp = new ArrayList<DataRiwayatPeminjaman>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            while(rs.next()) {
                DataRiwayatPeminjaman riwayat = new DataRiwayatPeminjaman();
                
                riwayat.setIdPeminjaman(rs.getInt("id_peminjaman"));
                riwayat.setNamaBuku(rs.getString("judul_buku"));
                riwayat.setNamaPeminjam(rs.getString("nama_peminjam"));
                riwayat.setTanggalPeminjaman(rs.getString("tanggal_peminjaman"));
                riwayat.setStatusBuku(rs.getString("status_buku"));
                
                drp.add(riwayat);
            }
        } catch (SQLException e) {
          Logger.getLogger(DataBuku.class.getName()).log(Level.SEVERE, null, e);
        }
        return drp;
    }
    
    public void updateStatusBuku(int idBuku) {
    PreparedStatement statement = null;

    try {
        statement = connection.prepareStatement(updateStatusBuku);
        statement.setInt(1, idBuku);

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
}
