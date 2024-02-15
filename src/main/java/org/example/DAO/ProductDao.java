package org.example.DAO;

import org.example.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Connection conn;
    public ProductDao(Connection conn){
        this.conn = conn;
    }

    public List<Product> getAllProducts(){
        List<Product> productResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Product");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                long productId = resultSet.getLong("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("price");
                String productSellerName = resultSet.getString("seller_name");
                Product p = new Product(productId, productName,productPrice,productSellerName);
                productResults.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productResults;
    }

    public void addProduct(Product p){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "Product (product_id, product_name, price,seller_name ) values (?, ?,?,?)");
            ps.setLong(1, p.getProductID());
            ps.setString(2, p.getProductName());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getSellerName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Product getProductById(long id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from product where product_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                long productId = rs.getLong("product_id");
                String productName = rs.getString("product_name");
                double productPrice = rs.getDouble("price");
                String productSellerName = rs.getString("seller_name");
                Product p = new Product(productId, productName,productPrice,productSellerName);
                return p;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Product updateProductById(Product p, long id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE product SET product_name = ?, price = ?, seller_name = ? where product_id = ?");
            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getSellerName());
            ps.setLong(4, id);
            ps.executeUpdate();
            return p;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Product deleteProductById(long id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM Product where product_id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
