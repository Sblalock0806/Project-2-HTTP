package org.example.Model;

import java.util.Objects;

public class Seller {

    private long sellerId ;
    private String sellerName;


    public Seller(){}

    public Seller(long sellerId, String sellerName) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return sellerId == seller.sellerId && Objects.equals(sellerName, seller.sellerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, sellerName);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                '}';
    }
}
