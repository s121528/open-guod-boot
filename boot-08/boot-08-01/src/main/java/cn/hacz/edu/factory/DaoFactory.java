package cn.hacz.edu.factory;

import cn.hacz.edu.dao.UserDaoI;
import cn.hacz.edu.dao.impl.UserDaoImpl;

import java.sql.Connection;

/**
 * DAO工厂模式
 *
 * @author guod
 * @version 1.0
 */
public class DaoFactory {
    /**
     * 功能描述：
     *
     * @param connection
     * @return
     */
    public static UserDaoI getUserDaoInstance(Connection connection) {
        return new UserDaoImpl(connection);
    }
}
