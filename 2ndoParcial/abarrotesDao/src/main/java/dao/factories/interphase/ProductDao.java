package dao.factories.interphase;

import dao.models.Product;

import java.util.List;

public interface ProductDao {

    public List<Product> moreThan(Integer criteria);

    public List<Product> lessThan(Integer criteria);

    public List<Product> sameClassMorePrice(String criteria1, Double criteria2);

    public List<Product> priceBetween(String field,Double criteria1, Double criteria2);
}
