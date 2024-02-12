package org.example.Service;

import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Product;
import java.util.ArrayList;
import java.util.List;


public class ProductService {

    List<Product> productList;


    SellerService sellerService;

    public ProductService(SellerService sellerService) {
        this.sellerService = sellerService;
        productList = new ArrayList<>();
    }


    /*
     * GET /product/
     * Get ALL Products
     */
    public List<Product> getProductList() {
        Main.log.info("Get list of all products");
        if (productList.isEmpty()) {
            Main.log.info("Request to get product list ran and No products found");
            System.out.println("No products found.");
        }
        return productList;
    }

    /*
     * GET /product/{id}
     *  Get a single product
     *  We should get a 404 error when we try to access a non-existed product.
     */

    public Product searchByProductID(long productID) {
        Main.log.info("Searching by product Id" + productID);
        for (Product product : productList) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }

    /*
    * POST /product/
    - Add a single product
    - Product ids should be non-null and unique
    - product names should be non-null
    - price should be over 0
    - Seller name should refer to an actually existing seller
     */

    public Product addProduct(Product p) throws ProductException, SellerException {
        if (!sellerService.isValidSeller(p.getSellerName())) {
            Main.log.warn("Product exception called due to Seller Name not in seller database");
            throw new ProductException("SellerName must exist in Seller database");
        }
        if (p.getProductName() == null || p.getProductName().isEmpty()) {
            Main.log.warn("Product exception called due to Product name is null");
            throw new ProductException("Product Name cannot be null");
        }
        if (p.getSellerName() == null || p.getSellerName().isEmpty()) {
            Main.log.warn("Product exception called due to Seller name is null");
            throw new ProductException("SellerName name cannot be null");
        }
        if (p.getPrice() <= 0.0) {
            Main.log.warn("Product exception called due to price being set to zero or less than zero");
            throw new ProductException(" Price may not be zero or less than zero");
        }
//set the product ID value
        long id = (long) (Math.random() * Long.MAX_VALUE);
        Main.log.info("Creating random Product ID" + id);
        p.setProductID(id);
//add the product to the list
        Main.log.info("Adding a product");
        productList.add(p);
        return (p);
    }

    /*
    PUT /product/{id}
    - Update a single product
    - product names should be non-null
    - price should be over 0
    - Seller name should refer to an actually existing seller
     */
    public void updateProduct(Product product, long id) throws ProductException, SellerException {
        Product productToUpdate = searchByProductID(id);
        if (sellerService.isValidSeller(product.getSellerName())) {
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setSellerName(product.getSellerName());
            Main.log.info("Updating a product");
        } else {
            Main.log.warn("Product Exception thrown because Seller Name must exist in the Seller database");
            throw new ProductException("SellerName must exist in Seller database");
        }
    }


/*
DELETE /product/{id}
- Delete a single product
- Delete should always return 200, regardless of if the item existed at the start or not. This is convention.
 */

    private Product getProductByID(long productID)    {
        for (Product product : productList) {
            if(product.getProductID() == productID){
                Main.log.info("Getting product by product id.");
                return product;
            }
        }
        return null;
    }
    public Product deleteProduct(Product p) throws ProductException{
        Product product = getProductByID(p.getProductID());
        if (product != null){
            Main.log.info("Deleting product by product id.");
            productList.remove(product);
            System.out.println("Product ID " + p.getProductID() + " has been deleted successfully");
        } else{
            Main.log.info("Product exception thrown because Product ID not found.");
            throw new ProductException("Product ID " + p.getProductID() + " not found.");
        }
        return product;
    }



}


