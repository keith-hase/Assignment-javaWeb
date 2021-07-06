/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.account;

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
public class AccountDAO implements Serializable {

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
            System.out.println("Close connection Error at AccountDAO");
        }
    }

    public String checkLogin(String username, String password) throws SQLException, NamingException {
        String lastname = "";
        try {
            // 1. Create connection
            conn = DBUtil.getConnection();
            // 2. Create SQL String
            String sql = "Select lastname from tblAccount where username=? and password=?";
            // 3. Create Statement
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            // 4. Execute Query
            rs = pre.executeQuery();
            // 
            if (rs.next()) {
                lastname = rs.getString("lastname");
            }

        } finally {
            closeConnection();
        }
        return lastname;
    }

    public List<AccountDTO> searchName(String nameSearch) throws SQLException, NamingException {
        List<AccountDTO> list = null;
        AccountDTO dto;
        String username, lastname;
        boolean role;
        try {
            list = new ArrayList<>();
            conn = DBUtil.getConnection();
            String sql = "Select username, lastname,role from tblAccount where lastname like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + nameSearch + "%");
            rs = pre.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
                lastname = rs.getString("lastname");
                role = rs.getBoolean("role");
                dto = new AccountDTO(username, lastname, role);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean deleteAccount(String id) throws SQLException, NamingException {
        boolean checkDelete = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "Delete from tblAccount where username = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            checkDelete = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkDelete;
    }

    public boolean updateAccount(String id, String lastName, boolean isRole) throws NamingException, SQLException {
        boolean check = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "Update tblAccount set lastname=?, role=? where username=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, lastName);
            pre.setBoolean(2, isRole);
            pre.setString(3, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertAccount(String username, String password, String lastname, boolean role) throws NamingException, SQLException {
        boolean check = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "Insert into tblAccount(username,password,lastname,role) values(?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            pre.setString(3, lastname);
            pre.setBoolean(4, role);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
