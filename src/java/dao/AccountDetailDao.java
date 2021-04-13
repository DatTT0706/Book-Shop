/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.AccountDetail;
import entity.Product;
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
public class AccountDetailDao implements IMethod<AccountDetail> {

    @Override
    public List<AccountDetail> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail getOne(int id) {
        String query = "SELECT * FROM Account_Details WHERE id = ?";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AccountDetail accountDetail = AccountDetail.builder()
                        .name(rs.getString(2))
                        .phone(rs.getString(3))
                        .gender(rs.getBoolean(4))
                        .address(rs.getString(5))
                        .build();
                return accountDetail;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    @Override
    public boolean add(AccountDetail obj) {
        String query = "Insert into Account_Details values(?, ?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, obj.getName());
            ps.setObject(2, obj.getPhone());
            ps.setObject(3, obj.isGender());
            ps.setObject(4, obj.getAddress());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }
    
    public int getNewestAccountDetailsId() {
        String query = "SELECT top (1) * from Account_Details ORDER BY id DESC";
       
        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean update(int id, AccountDetail obj) {
        String query = "Update Account_Details set name = ?,"
                + " phone = ?,"
                + " gender = ?, address = ? where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(5, id);
            ps.setObject(1, obj.getName());
            ps.setObject(2, obj.getPhone());
            ps.setObject(3, obj.isGender());
            ps.setObject(4, obj.getAddress());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
