package banana.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoMysql {
	private Connection connection = null;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/banana?useTimezone=true&serverTimezone=UTC";

    public ConexaoMysql() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        this.connection.setAutoCommit(false);
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                Logger.getLogger(ConexaoMysql.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void commit() throws SQLException {
        this.connection.commit();
        this.close();
    }

    public void rollback() {
        if (this.connection != null) {
            try {
                this.connection.rollback();
            } catch (SQLException e) {
                Logger.getLogger(ConexaoMysql.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

}
