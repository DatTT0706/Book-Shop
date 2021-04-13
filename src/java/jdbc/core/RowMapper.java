package jdbc.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapper {

    /**
     * Mapping rows of a ResultSet on a per-row basis, each row to a result object.
     *
     * @param rs
     * @param clazz
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public <T> T mapRow(ResultSet rs, Class<T> clazz)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field[] fields = clazz.getDeclaredFields();
        T object = clazz.getConstructor().newInstance();

        for (int i = 0; i < fields.length; ++i) {
            fields[i].setAccessible(true);
            try {
                Object value = rs.getObject(i + 1);
                fields[i].set(object, value);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return object;
    }
}
