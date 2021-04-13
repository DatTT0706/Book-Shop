/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author AnhDT
 */
public class PreparedStatementSetter {

    /**
     * Set value for PreparedStatement.
     *
     * @param ps
     * @param args
     * @throws SQLException
     */
    public void setValues(PreparedStatement ps, Object... args) throws SQLException {
        if (args != null) {
            for (int i = 0; i < args.length; ++i) {
                Object arg = args[i];
                ps.setObject(i + 1, arg);
            }
        }
    }
}
