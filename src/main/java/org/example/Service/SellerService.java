package org.example.Service;

import org.example.DAO.SellerDAO;
import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerService {

 //   public List<Seller> sellerList;
    SellerDAO sellerDAO;


    public SellerService(SellerDAO sellerDAO) {
//        this.sellerList = new ArrayList<>();
        this.sellerDAO =sellerDAO;
    }
    /*
     * GET /seller/
     * all sellers
     */
    public List<Seller> getAllSellers()  {
        Main.log.info("Getting all sellers");
        List<Seller> sellerList = sellerDAO.getAllSellers();
        return sellerList;

    }
    /*
     * POST  /seller/
     * Seller Names must be non-null and unique
     */
//    public void addSeller(SellerEntry seller) throws SellerException {
    public void addSeller(Seller s) throws SellerException {
        List<Seller> sellerList = sellerDAO.getAllSellers();
        if (s.getSellerId() == 0 ) {
            Main.log.warn("Seller's exception due to sellers name cannot be zero");
            throw new SellerException("Seller's name cannot be zero.");
        }
        if (s.getSellerName().isEmpty()) {
            Main.log.warn("Seller's exception due to sellers name cannot be Null");
            throw new SellerException("Seller's name cannot be Null.");
        }
        if (sellerList.contains(s)){
            Main.log.warn("Seller's exception due to duplicate sellers name.");
            throw new SellerException("Duplicate sellers name: " + s);
        }
        Main.log.info("Adding a seller" + s);
        sellerDAO.insertSeller(s);
    }

    public boolean isValidSeller(long sellerId) throws SellerException {
        List<Seller> sellerList = sellerDAO.getAllSellers();
        return sellerList.stream().anyMatch(seller -> seller.getSellerId() == sellerId);
    }
}
