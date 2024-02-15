package org.example.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Exception.ProductException;
import org.example.Exception.ProductNotFoundException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;

import java.sql.SQLException;
import java.util.List;

//
public class MainController {

    SellerService sellerService;
    ProductService productService;


    public  MainController(SellerService sellerService, ProductService productService){
       this.sellerService = sellerService;
       this.productService = productService;

    }

    public Javalin getAPI(){
        Javalin api = Javalin.create();

        api.get("/health",
                context ->
                {
                    context.result("the server is UP");
                }
        );

//        GET /seller/
//                - all sellers
        api.get("seller", context -> {
            List <Seller> sellerList = sellerService.getAllSellers();
            context.json(sellerList);

        });

//        GET /product/
//                - All products
        api.get("product", context -> {
            List <Product> productList = productService.getProductList();
            context.json(productList);
        });


//        GET /product/{id}
//                - Get a single product
//                - We should get a 404 error when we try to access a non-existed product.

        api.get("product/{id}",context -> {
            try {
                long id = Long.parseLong(context.pathParam("id"));
                Product p = productService.searchByProductID(id);
                if (p == null) {
                    context.status(404);
                } else {
                    context.json(p);
                    context.status(200);
                }
            }catch (ProductNotFoundException e) {
                context.status(404).result("invalid product ID");
            } catch(ProductException e){
                context.status(404).result("Product ID not found");
            }
        });

//        POST  /seller/
//                - Seller Names must be non-null and unique
        api.post("seller", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Seller s = om.readValue(context.body(), Seller.class);
                sellerService.addSeller(s);
                context.status(201);
                context.result("Seller successfully posted: " + s);
            }catch (JsonProcessingException e) {
                context.status(400);
            } catch (SellerException e) {
                context.result(e.getMessage());
                context.status(400);
            }
        });

//        POST /product/
//                - Add a single product
//                - Product ids should be non-null and unique
//                - product names should be non-null
//                - price should be over 0
//                - Seller name should refer to an actually existing seller

        api.post("product", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(context.body(), Product.class);
                Product newProduct = productService.addProduct(p);
                context.status(201);
                context.json(newProduct);
            }catch (JsonProcessingException | ProductException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });

//        PUT /product/{id}
//                - Update a single product
//                - product names should be non-null
//                - price should be over 0
//                - Seller name should refer to an actually existing seller
        api.put("product/{id}", context -> {

                long id = Long.parseLong(context.pathParam("id"));
                Product p = productService.searchByProductID(id);
                ObjectMapper om = new ObjectMapper();
                Product x =  om.readValue(context.body(), Product.class);
               if(p == null) {
                   context.status(404);
                   context.result("Product not found");
                } else {
                   try {
                       productService.updateProduct(x,id);
                       context.result("Product ID " + p.getProductID() + "has been updated successfully.");
                   } catch (NullPointerException | ProductException e) {
                       context.result(e.getMessage());
                       context.status(400);
                   }
               }
        });

//        DELETE /product/{id}
//                - Delete a single product
//                - Delete should always return 200, regardless of if the item existed at the start or not. This is convention.

        api.delete("product/{id}", context -> {
            try{
                long id = Long.parseLong(context.pathParam("id"));
                Product p = productService.searchByProductID(id);
                Product deleteProduct = productService.deleteProduct(p);
                context.status(200);
                context.json(deleteProduct);
            }catch (ProductException e){
                context.result(e.getMessage());
                context.status(200);
            }
        });

        return api;
    }





}
