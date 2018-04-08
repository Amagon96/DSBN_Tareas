package dao.conections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDao {
    private Connection conn = null;
    private static ConexionDao conexion;

    private ConexionDao() throws SQLException, ClassNotFoundException{
        this.createConexion();
    }

    public static ConexionDao getInstance() throws SQLException, ClassNotFoundException{
        if(conexion ==null){
            conexion = new ConexionDao();
        }
        return conexion;
    }

    private void createConexion()throws SQLException, ClassNotFoundException{
        String urlDatabase =  "jdbc:postgresql://localhost:5432/Abarrotes";
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(urlDatabase,  "postgres", "123456");
    }

    public Connection getConn() {
        return conn;
    }
}
