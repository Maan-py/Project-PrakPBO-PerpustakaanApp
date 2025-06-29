/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_Data;
import DAO_Implement.DataUsersImplement;
import java.sql.*;
import Connection.Connector;
import Model.DataUsers;

/**
 *
 * @author Maan
 */
public class DataUsersDAO implements DataUsersImplement {
    Connection connection;
    String select = "SELECT * FROM users WHERE username = ? AND password = ?";
    String cekUser = "SELECT * FROM users WHERE username = ?";
    String insert = "INSERT INTO users (id, username, password) VALUES (NULL, ?, ?)";
    
    public DataUsersDAO() {
        connection = Connector.connection();
    }
    
    @Override
    public DataUsers cekLogin(DataUsers d) {
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, d.getUsername());
            statement.setString(2, d.getPassword());
            rs = statement.executeQuery();
            
            if(rs.next()) {
                d.setIdUser(rs.getInt("id"));
                d.setUsername(rs.getString("username"));
                return d;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public boolean isUsernameExists(String username) {
       PreparedStatement statement = null;
       ResultSet rs = null;

        try {
            statement = connection.prepareStatement(cekUser, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            rs = statement.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public DataUsers registrasi(DataUsers d) {
        if(isUsernameExists(d.getUsername())) {
            return null;
        }
        
        PreparedStatement statement = null;
//        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, d.getUsername());
            statement.setString(2, d.getPassword());
            
            ResultSet rs = statement.getGeneratedKeys();
            
            while(rs.next()) {
                d.setIdUser(rs.getInt("id"));
                d.setUsername(rs.getString("username"));
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
        return d;
    }
}
