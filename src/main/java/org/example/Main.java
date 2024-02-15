package org.example;

import Util.ConnectionSingleton;
import io.javalin.Javalin;
import org.example.Controller.MainController;
import org.example.DAO.ProductDao;
import org.example.DAO.SellerDAO;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;

public class Main {
    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Connection conn = ConnectionSingleton.getConnection();
        ProductDao productDao = new ProductDao(conn);
        SellerDAO sellerDAO = new SellerDAO(conn);
        SellerService sellerService = new SellerService(sellerDAO);
        ProductService productService = new ProductService(sellerService, productDao);
        MainController mainController = new MainController(sellerService, productService);
        Javalin api = mainController.getAPI();
        api.start(9002);
    }
}