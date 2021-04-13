/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cart;
import java.util.List;
import entity.OrdersDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.SQLServerConnection;

/**
 *
 * @author TRANTATDAT
 */
public class OrdersDetailsDao {

    public boolean add(List<Cart> listCart, int orderId) {

        String query = "Insert into Order_Details (order_id, product_id, product_name, quantity, product_price)"
                + " values(?, ?, ?, ?, ?)";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            for (Cart c : listCart) {
                ps.setObject(1, orderId);
                ps.setObject(2, c.getId());
                ps.setObject(3, c.getName());
                ps.setObject(4, c.getQuantity());
                ps.setObject(5, c.getPrice());
                //add nhieu record trong 1 lan mo cong
                ps.addBatch();
            }

            ps.executeBatch();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;
    }

    public List<OrdersDetails> getDetailsOfOrder(int orderId) {
        String query = "SELECT * FROM Order_Details WHERE order_id = ?";

        List<OrdersDetails> lsOrderDetailses = new ArrayList<>();

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrdersDetails odDetails = OrdersDetails.builder()
                        .id(rs.getInt(1))
                        .orderId(rs.getInt(2))
                        .productId(rs.getInt(3))
                        .productName(rs.getString(4))
                        .quantity(rs.getInt(5))
                        .productPrice(rs.getDouble(6))
                        .build();
                lsOrderDetailses.add(odDetails);
            }

            return lsOrderDetailses;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }
    
    
    public boolean remove(int orderId) {
        String query = "Delete from Order_Details where order_id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, orderId);

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

}
