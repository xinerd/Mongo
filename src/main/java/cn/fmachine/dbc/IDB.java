package cn.fmachine.dbc;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 4/30/16
 */

/**
 * Database
 * @param <Connection> used to execute CRUD operations
 */
public interface IDB<Connection> {

    /**
     * Example: Oracle, MySQL
     * @return a connection that can execute CRUD operations.
     */
    Connection getConnection();

    void closeConnection();
}
