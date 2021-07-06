/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.Item;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import kiet.util.DBUtil;

/**
 *
 * @author keith
 */
public class ItemDAO implements Serializable {

    Connection conn;
    PreparedStatement pre;
    ResultSet rs;

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }

    public List<ItemDTO> getProduct() throws NamingException, SQLException {
        List<ItemDTO> list;
        ItemDTO dto;
        try {
            conn = DBUtil.getConnection();
            String sql = "Select id,name,price from tblProduct where status=?";
            pre = conn.prepareStatement(sql);
            pre.setBoolean(1, true);
            rs = pre.executeQuery();
            list = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                float price = rs.getFloat("price");
                dto = new ItemDTO(name, id, price);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ItemDTO getProductById(int id) throws NamingException, SQLException {

        ItemDTO dto = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "Select id,name,price from tblProduct where id=?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();

            if (rs.next()) {
                int idSearch = rs.getInt("id");
                String name = rs.getString("name");

                float price = rs.getFloat("price");
                dto = new ItemDTO(name, id, price);

            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
   

}
