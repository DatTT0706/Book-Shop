/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.core;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AnhDT
 */
public class JdbcTemplate {

    /**
     * Get all records using PreparedStatement.
     *
     * @param ps
     * @param clazz
     * @param <T>
     * @return List T
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> List<T> queryForList(PreparedStatement ps, Class<T> clazz)
            throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return getListObject(ps, clazz);
    }

    /**
     * Get all records using PreparedStatement using given arguments..
     *
     * @param ps
     * @param clazz
     * @param args
     * @param <T>
     * @return List T
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> List<T> queryForList(PreparedStatement ps, Class<T> clazz, Object... args)
            throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new PreparedStatementSetter().setValues(ps, args);
        return getListObject(ps, clazz);
    }

    private <T> List<T> getListObject(PreparedStatement ps, Class<T> clazz)
            throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ResultSet rs = ps.executeQuery();
        List<T> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new RowMapper().mapRow(rs, clazz));
        }
        return list.size() > 0 ? list : null;
    }

    /**
     * Get one record using PreparedStatement.
     *
     * @param ps
     * @param clazz
     * @param args
     * @param <T>
     * @return T
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T queryForObject(PreparedStatement ps, Class<T> clazz, Object... args)
            throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new PreparedStatementSetter().setValues(ps, args);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new RowMapper().mapRow(rs, clazz);
        }
        return null;
    }

    /**
     * insert, update and delete records using PreparedStatement using given arguments.
     *
     * @param ps
     * @param args
     * @return
     * @throws SQLException
     */
    public int update(PreparedStatement ps, Object... args) throws SQLException {
        new PreparedStatementSetter().setValues(ps, args);
        return ps.executeUpdate();
    }
}
