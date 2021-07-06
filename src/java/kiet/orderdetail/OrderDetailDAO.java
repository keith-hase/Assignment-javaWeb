/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import kiet.Item.ItemDAO;
import kiet.Item.ItemDTO;
import kiet.util.DBUtil;

/**
 *
 * @author keith
 */
public class OrderDetailDAO implements Serializable {

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
             System.out.println("Close connection Error at OrderDetailDAO");
        }
    }

    public List<OrderDetailDTO> addlistItemCheckout(Map<String, Integer> item) throws NamingException, SQLException {
        List<OrderDetailDTO> list = null;
        for (String key : item.keySet()) {
            int id = Integer.parseInt(key);
            ItemDAO dao = new ItemDAO();
            ItemDTO dto = dao.getProductById(id);
            String nameItem = dto.getName();
            int quantity = item.get(key);
            float price = dto.getPrice();
            float total = price * quantity;
            OrderDetailDTO dtoOrder = new OrderDetailDTO(nameItem, quantity, price, total);
            // Nếu list ko có (== null) mới khởi tạo còn có rồi thì chỉ cần add vào
            if (list == null) {
                 list = new ArrayList<>();
            }
            list.add(dtoOrder);

       
        }
        // kiem tra list
      
        return list;
    }

    public boolean checkoutItem(OrderDetailDTO dto) throws NamingException, SQLException {
        boolean check = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "Insert into tblOrderItem(name,quantity,price,total) values(?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, dto.getNameItem());
            pre.setInt(2, dto.getQuantity());
            pre.setFloat(3, dto.getPrice());
            pre.setFloat(4, dto.getTotal());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }
}
