/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO_Implement;

import Model.*;
import java.util.List;

/**
 *
 * @author Maan
 */
public interface DataBukuImplement {
    public void insert(DataBuku db);
    public List<DataBuku> getAll(DataBuku db);
    public void update(DataBuku db);
    public void delete(int idBuku);
}
