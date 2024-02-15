package org.example.DAO;

import org.example.Exception.SellerException;
import org.example.Model.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO {
    Connection conn;

    public SellerDAO(Connection conn){
        this.conn = conn;
    }

    public List<Seller> getAllSellers(){
        List<Seller> sellerResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Seller");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                long sellerId = resultSet.getLong("seller_id");
                String sellerName = resultSet.getString("seller_name");
                Seller newSeller = new Seller(sellerId,sellerName);
                sellerResults.add(newSeller);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sellerResults;
    }

    public void insertSeller(Seller s){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "Seller (seller_id,seller_name) values (?,?)");
            ps.setLong(1, s.getSellerId());
            ps.setString(2, s.getSellerName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
