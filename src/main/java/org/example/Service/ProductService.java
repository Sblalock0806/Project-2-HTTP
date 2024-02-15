package org.example.Service;

import org.example.DAO.ProductDao;
import org.example.Exception.ProductException;
import org.example.Exception.ProductNotFoundException;
import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductService {

    //List<Product> productList;

    ProductDao productDao;

    SellerService sellerService;

    public ProductService(SellerService sellerService, ProductDao productDao) {
        this.sellerService = sellerService;
        this.productDao =productDao;

    }


    /*
     * GET /product/
     * Get ALL Products
     */
//    public List<Product> getProductList() {
//        Main.log.info("Get list of all products");
//        if (productList.isEmpty()) {
//            Main.log.info("Request to get product list ran and No products found");
//            System.out.println("No products found.");
//        }
//        return productList;
//    }

    //Latest change to use SQL/DAO
    public List<Product> getProductList() {
        List<Product> productList = productDao.getAllProducts();
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

    public Product searchByProductID(long productID) throws ProductException, ProductNotFoundException, SQLException {
        Main.log.info("Searching by product Id " + productID);
        Product p = productDao.getProductById(productID);
        if(p == null){
            throw new ProductNotFoundException("No product found with that ID");
        }else{
            return p;
        }
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
        if (!sellerService.isValidSeller(p.getSellerID())) {
            Main.log.warn("Product exception called due to Seller Name not in seller database");
            throw new ProductException("SellerName must exist in Seller database");
        }
        if (p.getProductName() == null || p.getProductName().isEmpty()) {
            Main.log.warn("Product exception called due to Product name is null");
            throw new ProductException("Product Name cannot be null");
        }
        if ((p.getSellerID() == 0)) {
            Main.log.warn("Product exception called due to Seller id is null or zero");
            throw new ProductException("SellerID name cannot be zero");
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
        productDao.addProduct(p);
        return (p);
    }

    /*
    PUT /product/{id}
    - Update a single product
    - product names should be non-null
    - price should be over 0
    - Seller name should refer to an actually existing seller
     */
//    public void updateProduct(Product product, long id) throws ProductException, SellerException {
//        Product productToUpdate = searchByProductID(id);
//        if (sellerService.isValidSeller(product.getSellerName())) {
//            productToUpdate.setProductName(product.getProductName());
//            productToUpdate.setPrice(product.getPrice());
//            productToUpdate.setSellerName(product.getSellerName());
//            Main.log.info("Updating a product");
//        } else {
//            Main.log.warn("Product Exception thrown because Seller Name must exist in the Seller database");
//            throw new ProductException("SellerName must exist in Seller database");
//        }
//    }

    public void updateProduct(Product product, long id) throws ProductException, SellerException {
        Product productToUpdate = productDao.updateProductById(product,id);
        if (sellerService.isValidSeller(product.getSellerID())) {
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setSellerID(product.getSellerID());
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

    private Product getProductByID(long productID) throws ProductException, SQLException {
        Product p = productDao.getProductById(productID);
        if(p == null){
            throw new ProductException("No product found with that ID");
        }else{
            return p;
        }
//        for (Product product : productList) {
//            if(product.getProductID() == productID){
//                Main.log.info("Getting product by product id.");
//                return product;
//            }else{
//                throw new ProductException("No product found with that ID");
//            }
//        }
//        return null;
    }
    public Product deleteProduct(Product p) throws ProductException, SQLException {
        Product product = getProductByID(p.getProductID());
        if (product != null){
            Main.log.info("Deleting product by product id.");
            productDao.deleteProductById(product.getProductID());
            System.out.println("Product ID " + product.getProductID() + " has been deleted successfully");
            return product;
        } else{
            Main.log.info("Product exception thrown because Product ID not found.");
            throw new ProductException("Product ID " + product.getProductID() + " not found.");
        }

    }



}


