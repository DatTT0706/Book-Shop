/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.FR_category;

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
public class FR_categoryDao implements IMethod<FR_category> {

    @Override
    public List<FR_category> getAll() {
        String query = "SELECT * FROM FR_categories";
        List<FR_category> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FR_category c = FR_category.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .status(rs.getInt(3))
                        .note(rs.getString(4))
                        .build();
                ls.add(c);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public FR_category getOne(int id) {
        String query = "SELECT * FROM FR_categories where id = ?";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FR_category c = FR_category.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .status(rs.getInt(3))
                        .note(rs.getString(4))
                        .build();
                return c;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean add(FR_category obj) {
        String query = "Insert into FR_categories values(?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, obj.getName());
            ps.setObject(2, obj.getStatus());
            ps.setObject(3, obj.getNote());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean update(int id, FR_category obj) {
        String query = "Update FR_categories set name = ?,"
                + " status = ?,"
                + " note = ?"
                + " where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setObject(1, obj.getName());
            ps.setObject(2, obj.getStatus());
            ps.setObject(3, obj.getNote());
            ps.setObject(4, id);
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        String query = "Delete from FR_categories where id = ?";
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
