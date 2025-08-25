package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
    private static final String URL = "jdbc:mysql://localhost:3306/recensement";
    private static final String USER = "root";
    private static final String PASSWORD = "pianor226"; 

    public static Connection getConnection() throws SQLException {
        try {
            // Chargement explicite du driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Pilote JDBC MySQL non trouvé.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
