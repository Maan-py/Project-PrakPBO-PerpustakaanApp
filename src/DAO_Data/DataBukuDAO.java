/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_Data;
import Connection.Connector;
import DAO_Implement.DataBukuImplement;
import Model.DataBuku;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maan
 */
public class DataBukuDAO implements DataBukuImplement {
    Connection connection;
    String insert = "INSERT INTO buku (judul, genre, tahun, penulis, link_cover, status) VALUES (?, ?, ?, ?, ?, ?)";
    String select = "SELECT * FROM buku WHERE status !=  'Dihapus'";
    String update = "UPDATE buku SET judul = ?, genre = ?, tahun = ?, penulis = ?, link_cover = ?, status = ? WHERE id = ?";
    String delete = "UPDATE buku SET status = 'Dihapus' WHERE id = ?";
    
    public DataBukuDAO() {
        connection = Connector.connection();
    }

    @Override
    public void insert(DataBuku db) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, db.getJudul());
            statement.setString(2, db.getGenre());
            statement.setString(3, db.getTahun());
            statement.setString(4, db.getPenulis());
            statement.setString(5, db.getLink_cover());
            statement.setString(6, db.getStatus());
            
            ResultSet rs = statement.getGeneratedKeys();
            
            while(rs.next()) {
                db.setIdBuku(1);
            }
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<DataBuku> getAll(DataBuku b) {
        List<DataBuku> db = null;
        
        try {
            db = new ArrayList<DataBuku>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            while(rs.next()) {
                DataBuku buku = new DataBuku();
                
                buku.setIdBuku(rs.getInt("id"));
                buku.setJudul(rs.getString("judul"));
                buku.setGenre(rs.getString("genre"));
                buku.setTahun(rs.getString("tahun"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setLink_cover(rs.getString("link_cover"));
                buku.setStatus(rs.getString("status"));
                
                db.add(buku);
            }
        } catch (SQLException e) {
          Logger.getLogger(DataBuku.class.getName()).log(Level.SEVERE, null, e);
        }
        return db;
    }

    @Override
    public void update(DataBuku db) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, db.getJudul());
            statement.setString(2, db.getGenre());
            statement.setString(3, db.getTahun());
            statement.setString(4, db.getPenulis());
            statement.setString(5, db.getLink_cover());
            statement.setString(6, db.getStatus());
            statement.setInt(7, db.getIdBuku());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int idBuku) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareCall(delete);
            statement.setInt(1, idBuku);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
