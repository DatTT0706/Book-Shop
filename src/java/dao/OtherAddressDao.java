/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.OtherAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.SQLServerConnection;

/**
 *
 * @author TRANTATDAT
 */
public class OtherAddressDao {

    public boolean add(OtherAddress obj) {
        String query = "Insert into Other_Address values(?, ?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, obj.getName());
            ps.setObject(2, obj.getPhoneNumber());
            ps.setObject(3, obj.getAddress());
            ps.setObject(4, obj.getOrderId());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    public OtherAddress getOtherAddressByOrderId(int orderId) {
        String query = "SELECT * FROM Other_Address where order_id = ?";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OtherAddress a = OtherAddress.builder()
                        .name(rs.getString(2))
                        .phoneNumber(rs.getString(3))
                        .address(rs.getString(4))
                        .orderId(rs.getInt(5))
                        .build();
                return a;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }
    
    public boolean remove(int orderId) {
        String query = "Delete from Other_Address where order_id = ?";
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
