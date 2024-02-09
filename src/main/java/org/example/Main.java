package org.example;

import io.javalin.Javalin;
import org.example.Controller.MainController;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        SellerService sellerService = new SellerService();
        ProductService productService = new ProductService(sellerService);
        MainController mainController = new MainController(sellerService, productService);
        Javalin api = mainController.getAPI();
        api.start(9002);
    }
}