/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author TRANTATDAT
 */
public class ProductDao implements IMethod<Product> {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM Products";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int countTotalProduct() {
        return getAll().size();
    }

    public int getNewAddProductId() {
        String query = "SELECT top (1) * from Products ORDER BY id DESC";

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

    public List<Product> getProductPerPage(int pageIndex, int numberProduct) {
        String sql = "SELECT * FROM Products WHERE status = 1 OR status = 3 ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
        List<Product> ls = new ArrayList<>();

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pageIndex);
            ps.setInt(2, numberProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product> getProductPerPageIncreasingPrice(int pageIndex, int numberProduct) {
        String sql = "SELECT * FROM Products WHERE status = 1 OR status = 3 ORDER BY price ASC OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
        List<Product> ls = new ArrayList<>();

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pageIndex);
            ps.setInt(2, numberProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product> getProductPerPageDecreasingPrice(int pageIndex, int numberProduct) {
        String sql = "SELECT * FROM Products WHERE status = 1 OR status = 3 ORDER BY price DESC OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
        List<Product> ls = new ArrayList<>();

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pageIndex);
            ps.setInt(2, numberProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product> getAllByType(int id) {
        String query = "SELECT * FROM Products WHERE type_id = ? AND (status = 1 OR status = 3)";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Product> getAllByFrCategory(int id) {
        String query = "SELECT * FROM Products WHERE FR_category_id = ? AND (status = 1 OR status = 3)";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Product> getAllByVNCategory(int id) {
        String query = "SELECT * FROM Products WHERE VN_category_id = ? AND (status = 1 OR status = 3)";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Product> getSameAuthor(int id) {
        String query = "SELECT * FROM Products WHERE author_id = ? AND (status = 1 OR status = 3)";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Product> getSamePublisher(int id) {
        String query = "SELECT * FROM Products WHERE publisher_id = ? AND (status = 1 OR status = 3)";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Product> getThreeNewProduct() {
        String query = "SELECT top (3) * from Products WHERE status = 1 OR status = 3  ORDER BY id DESC ";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Product getOne(int id) {

        String query = "SELECT * FROM Products where id = ? AND (status = 1 OR status = 3)";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                return p;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean add(Product obj) {
        String query = "Insert into Products values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, obj.getTypeId());
            ps.setObject(2, obj.getAuthorId());
            ps.setObject(3, obj.getPublisherId());
            ps.setObject(4, obj.getVnCategoryId());
            ps.setObject(5, obj.getFrCategoryId());
            ps.setObject(6, obj.getName());
            ps.setObject(7, obj.getPrice());
            ps.setObject(8, obj.getQuantity());
            ps.setObject(9, obj.getImgName());
            ps.setObject(10, obj.getDescription());
            ps.setObject(11, obj.getNote());
            ps.setObject(12, obj.getStatus());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Product obj) {
        String query = "Update Products set type_id = ?,"
                + " author_id = ?,"
                + " publisher_id = ?,"
                + " VN_category_id = ?,"
                + " FR_category_id = ?,"
                + " name = ?,"
                + " price = ?,"
                + " quantity = ?,"
                + " img_name = ?,"
                + " description = ?,"
                + " note = ?,"
                + " status = ? where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(13, id);
            ps.setObject(1, obj.getTypeId());
            ps.setObject(2, obj.getAuthorId());
            ps.setObject(3, obj.getPublisherId());
            ps.setObject(4, obj.getVnCategoryId());
            ps.setObject(5, obj.getFrCategoryId());
            ps.setObject(6, obj.getName());
            ps.setObject(7, obj.getPrice());
            ps.setObject(8, obj.getQuantity());
            ps.setObject(9, obj.getImgName());
            ps.setObject(10, obj.getDescription());
            ps.setObject(11, obj.getNote());
            ps.setObject(12, obj.getStatus());
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        String query = "Delete from Products where id = ?";
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

    public List<Product> searchByName(String name) {
        String query = "SELECT * FROM Products WHERE name LIKE ? AND (status = 1 OR status = 3)";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                //check discount and change price
                if (p.getStatus() == 3) {
                    p.setPrice(p.getPrice() - (p.getPrice() * 0.1));
                }
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Product> searchByNameAdmin(String name) {
        String query = "SELECT * FROM Products WHERE name LIKE ?";
        List<Product> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .typeId(rs.getInt(2))
                        .authorId(rs.getInt(3))
                        .publisherId(rs.getInt(4))
                        .vnCategoryId(rs.getInt(5))
                        .frCategoryId(rs.getInt(6))
                        .name(rs.getString(7))
                        .price(rs.getDouble(8))
                        .quantity(rs.getInt(9))
                        .imgName(rs.getString(10))
                        .description(rs.getString(11))
                        .note(rs.getString(12))
                        .status(rs.getInt(13))
                        .build();
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
