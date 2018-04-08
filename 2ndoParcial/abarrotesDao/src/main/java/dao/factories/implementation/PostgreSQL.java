package dao.factories.implementation;

import dao.conections.ConexionDao;
import dao.factories.interphase.ProductDao;
import dao.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQL implements ProductDao{
    @Override
    public List<Product> moreThan(Integer criteria) {

        List<Product> productos = new ArrayList<>();
        try {
            ConexionDao conexion = ConexionDao.getInstance();
            Statement st = conexion.getConn().createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %d",Product.F_EX_MORE_THAN, criteria));
            while(rs.next()){
                productos.add(makeProduct(rs));
            }
        }catch (ClassNotFoundException | SQLException ex){

        }

        return productos;

    }

    @Override
    public List<Product> lessThan(Integer criteria) {

        List<Product> productos = new ArrayList<>();
        try {
            ConexionDao conexion = ConexionDao.getInstance();
            Statement st = conexion.getConn().createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %d",Product.F_EX_LESS_THAN, criteria));
            while(rs.next()){
                productos.add(makeProduct(rs));
            }
        }catch (ClassNotFoundException | SQLException ex){

        }

        return productos;

    }

    @Override
    public List<Product> sameClassMorePrice(String criteria1, Double criteria2) {
        List<Product> productos = new ArrayList<>();
        try {
            ConexionDao conexion = ConexionDao.getInstance();
            Statement st = conexion.getConn().createStatement();
            ResultSet rs =  st.executeQuery(String.format("%s '%s' AND %s %f ",Product.F_SAME_CLASS,criteria1,Product.S_PRICE_MORE_THAN, criteria2));
            while(rs.next()){
                productos.add(makeProduct(rs));
            }
        }
        catch (ClassNotFoundException | SQLException ex){

        }
        return productos;
    }

    public List<Product> priceBetween(String field,Double criteria1, Double criteria2){
        List<Product> productos = new ArrayList<>();
        try {
            ConexionDao conexion = ConexionDao.getInstance();
            Statement st = conexion.getConn().createStatement();
            ResultSet rs =  st.executeQuery(String.format("%s WHERE %s BETWEEN %f AND %f ",Product.Q_ALL,field,criteria1, criteria2));
            while(rs.next()){
                productos.add(makeProduct(rs));
            }
        }
        catch (ClassNotFoundException | SQLException ex){

        }
        return productos;
    }

    private Product makeProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        Integer i = 1;
        product.setClave(rs.getLong(i++));
        product.setDescripcion(rs.getString(i++));
        product.setPrecio(rs.getDouble(i++));
        product.setClasificacion(rs.getString(i++));
        product.setExistencia(rs.getInt(i++));
        product.setExistencia_min(rs.getInt(i++));
        product.setExistencia_max(rs.getInt(i++));
        return product;
    }
}
