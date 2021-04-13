/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Author;
import entity.Coupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author TRANTATDAT
 */
public class CouponDao {

    public List<Coupon> getAll() {
        String query = "SELECT * FROM Coupon";
        List<Coupon> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coupon a = Coupon.builder()
                        .id(rs.getInt(1))
                        .code(rs.getString(2))
                        .accountId(rs.getInt(3))
                        .used(rs.getBoolean(4))
                        .build();
                ls.add(a);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Coupon> getCouponOfAccount(int accountId) {
        String query = "SELECT * FROM Coupon WHERE account_id = ? and used = 0";
        List<Coupon> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coupon a = Coupon.builder()
                        .id(rs.getInt(1))
                        .code(rs.getString(2))
                        .accountId(rs.getInt(3))
                        .used(rs.getBoolean(4))
                        .build();
                ls.add(a);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean add(Coupon obj) {
        String query = "Insert into Coupon values(?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, obj.getCode());
            ps.setObject(2, obj.getAccountId());
            ps.setObject(3, obj.isUsed());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    public Coupon findByCode(String code) {
        String query = "SELECT * FROM Coupon WHERE code = ?";

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, code);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coupon a = Coupon.builder()
                        .id(rs.getInt(1))
                        .code(rs.getString(2))
                        .accountId(rs.getInt(3))
                        .used(rs.getBoolean(4))
                        .build();
                return a;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateStatus(int id, Coupon obj) {
        String query = "Update Coupon set used = ? where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(2, id);
            ps.setObject(1, obj.isUsed());
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }
    
    public boolean remove(int id) {
        String query = "Delete from Coupon where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

}
