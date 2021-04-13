/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author TRANTATDAT
 */
public class OrderDao {

    public List<Order> getAll() {
        String query = "SELECT * FROM Orders";

        List<Order> lsOrder = new ArrayList<>();

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = Order.builder()
                        .id(rs.getInt(1))
                        .accountId(rs.getInt(2))
                        .totalMoney(rs.getDouble(3))
                        .note(rs.getString(4))
                        .status(rs.getInt(5))
                        .build();
                lsOrder.add(o);
            }

            return lsOrder;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int countTotalOrder() {
        return getAll().size();
    }

    public int addOrder(Order order) {

        String query = "Insert into Orders values(?, ?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, order.getAccountId());
            ps.setObject(2, order.getTotalMoney());
            ps.setObject(3, order.getNote());
            ps.setObject(4, order.getStatus());

            check = ps.executeUpdate();
            if (check > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return 0;
    }

    public boolean removeOrder(int orderId) {
        String query = "Delete from Orders where id = ?";
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

    public List<Order> getAllByAccountId(int accountId) {
        String query = "SELECT * FROM Orders WHERE account_id = ?";

        List<Order> lsOrder = new ArrayList<>();

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = Order.builder()
                        .id(rs.getInt(1))
                        .accountId(rs.getInt(2))
                        .totalMoney(rs.getDouble(3))
                        .note(rs.getString(4))
                        .status(rs.getInt(5))
                        .build();
                lsOrder.add(o);
            }

            return lsOrder;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public boolean updateStatus(int id, int status) {
        String query = "Update Orders set"
                + " status = ?"
                + " where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, status);
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    public boolean remove(int orderId) {
        String query = "Delete from Order where id = ?";
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
