/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
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
public class AccountDao {

    public Account login(String email, String password) {
        String query = "SELECT * FROM Accounts WHERE Email = ? and Password = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, email);
            ps.setObject(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account ac = Account.builder()
                        .id(rs.getInt(1))
                        .email(rs.getString(2))
                        .password(rs.getString(3))
                        .accountDetailId(rs.getInt(4))
                        .roleId(rs.getInt(5))
                        .activeCode(rs.getString(6))
                        .status(rs.getInt(7))
                        .build();
                return ac;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account findByEmail(String email) {
        String query = "SELECT * FROM Accounts WHERE Email = ? ";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account ac = Account.builder()
                        .id(rs.getInt(1))
                        .email(rs.getString(2))
                        .password(rs.getString(3))
                        .accountDetailId(rs.getInt(4))
                        .roleId(rs.getInt(5))
                        .activeCode(rs.getString(6))
                        .status(rs.getInt(7))
                        .build();
                return ac;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean changePassword(String newPassword, int accountId) {
        int isCheck = 0;
        String query = "UPDATE Accounts set Password = ? WHERE id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, newPassword);
            ps.setObject(2, accountId);
            isCheck = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCheck > 0;
    }

    public boolean changeStatus(int newStatus, int accountId) {
        int isCheck = 0;
        String query = "UPDATE Accounts set status = ? WHERE id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, newStatus);
            ps.setObject(2, accountId);
            isCheck = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCheck > 0;
    }

    public boolean add(Account obj) {
        String query = "Insert into Accounts values(?, ?, ?, ?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, obj.getEmail());
            ps.setObject(2, obj.getPassword());
            ps.setObject(3, obj.getAccountDetailId());
            ps.setObject(4, obj.getRoleId());
            ps.setObject(5, obj.getActiveCode());
            ps.setObject(6, obj.getStatus());
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }
    
    public boolean updateStatus(int id, int status) {
        String query = "Update Accounts set status = ? where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(2, id);
            ps.setObject(1, status);
            
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    public List<Account> getAll() {
        String query = "SELECT * FROM Accounts";
        List<Account> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account ac = Account.builder()
                        .id(rs.getInt(1))
                        .email(rs.getString(2))
                        .password(rs.getString(3))
                        .accountDetailId(rs.getInt(4))
                        .roleId(rs.getInt(5))
                        .activeCode(rs.getString(6))
                        .status(rs.getInt(7))
                        .build();
                ls.add(ac);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int countTotalAccount() {
        return getAll().size();
    }
}
