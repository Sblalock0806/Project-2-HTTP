package org.example.Service;

import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerService {

    public List<Seller> sellerList;


    public SellerService() {
        this.sellerList = new ArrayList<>();
    }

    /*
     * GET /seller/
     * all sellers
     */
    public List<Seller> getAllSellers() {
        Main.log.info("Getting all sellers");
        return sellerList;
    }
    /*
     * POST  /seller/
     * Seller Names must be non-null and unique
     */
//    public void addSeller(SellerEntry seller) throws SellerException {
    public Seller addSeller(Seller s) throws SellerException {
        if (s.getSellerName().isEmpty()) {
            Main.log.warn("Seller's exception due to sellers name cannot be Null");
            throw new SellerException("Seller's name cannot be Null.");
        }
        if (sellerList.contains(s)){
            Main.log.warn("Seller's exception due to duplicate sellers name.");
            throw new SellerException("Duplicate sellers name: " + s);
        }
        Main.log.info("Adding a seller" + s);
        sellerList.add(s);
        return s;
    }

    public boolean isValidSeller(String sellerName) throws SellerException {
        return sellerList.stream().anyMatch(seller -> seller.getSellerName().equals(sellerName));
    }
}
