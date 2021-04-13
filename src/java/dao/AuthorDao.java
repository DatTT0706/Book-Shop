/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Author;

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
public class AuthorDao implements IMethod<Author> {

    @Override
    public List<Author> getAll() {
        String query = "SELECT * FROM Authors";
        List<Author> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Author a = Author.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .status(rs.getInt(3))
                        .note(rs.getString(4))
                        .build();
                ls.add(a);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Author getOne(int id) {
        String query = "SELECT * FROM Authors where id = ?";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Author a = Author.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .status(rs.getInt(3))
                        .note(rs.getString(4))
                        .build();
                return a;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return null;
    }

    @Override
    public boolean add(Author obj) {
        String query = "Insert into Authors values(?, ?, ?)";
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
    public boolean update(int id, Author obj) {
        String query = "Update Authors set  name = ?,"
                + " status = ?,"
                + " note = ? where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(4, id);
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
    public boolean remove(int id) {
        String query = "Delete from Authors where id = ?";
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
