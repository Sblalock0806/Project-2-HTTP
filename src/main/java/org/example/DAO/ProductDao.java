package org.example.DAO;

import org.example.Exception.ProductException;
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
                long sellerId = resultSet.getLong("seller_id");
                Product p = new Product(productId, productName,productPrice,sellerId);
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
                    "Product (product_id, product_name, price,seller_Id ) values (?, ?,?,?)");
            ps.setLong(1, p.getProductID());
            ps.setString(2, p.getProductName());
            ps.setDouble(3, p.getPrice());
            ps.setLong(4, p.getSellerID());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Product getProductById(long id) throws SQLException{
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from product where product_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                long productId = rs.getLong("product_id");
                String productName = rs.getString("product_name");
                double productPrice = rs.getDouble("price");
                long productSellerId = rs.getLong("seller_id");
                Product p = new Product(productId, productName,productPrice,productSellerId);
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
                    "UPDATE product SET product_name = ?, price = ?, seller_id = ? where product_id = ?");
            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getPrice());
            ps.setLong(3, p.getSellerID());
            ps.setLong(4, id);
            ps.executeUpdate();
            return p;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Product deleteProductById(long x){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM Product where product_id = ?");
            ps.setLong(1, x);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
