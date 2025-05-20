/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Maan
 */
public class Connector {
    static Connection con;
    
    public static Connection connection() {
        if(con == null) {
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("perpus_db");
            data.setUser("root");
            data.setPassword("");
            
             try {
                con = data.getConnection();;
                 System.out.println("Koneksi Berhasil!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Koneksi Gagal!");
            }
        }
        
       return con;
    }
}
