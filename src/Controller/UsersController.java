/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO_Data.DataUsersDAO;
import View.LoginPage;
import Model.*;

/**
 *
 * @author Maan
 */
public class UsersController {
    LoginPage loginFrame;
    DataUsersDAO implementUsers;

    public UsersController(LoginPage loginFrame) {
        this.loginFrame = loginFrame;
        implementUsers = new DataUsersDAO();
    }
    
    public DataUsers cekLogin() {
        DataUsers du = new DataUsers();
        du.setUsername(loginFrame.getjUsernameField().getText());
        du.setPassword(loginFrame.getjPasswordField().getText());
        
        return implementUsers.cekLogin(du);
    }
}
