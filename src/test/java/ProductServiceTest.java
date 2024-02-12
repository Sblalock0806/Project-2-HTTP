import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import java.util.List;

import static org.junit.Assert.*;
public class ProductServiceTest {

    ProductService productService;
    SellerService sellerService;
    @Before
    public void setup() {
        productService = new ProductService(new SellerService());
        sellerService = new SellerService();
    }
    //Test get on Empty List
    @Test
    public void testProductServiceEmptyAtStart() {
        List<Product> productList = productService.getProductList();
//        ensure that at the start, there are no Product
        Assert.assertTrue(productList.isEmpty());
    }


    @Test
    public void testAddProduct() throws ProductException, SellerException {
        SellerService sellerService1 = new SellerService();
        sellerService1.addSeller(new Seller("Seller A"));
        ProductService productService1 = new ProductService(sellerService1);
        Product p = new Product(11021,"test product", 50.0, "Seller A");
        Product addedProduct = productService1.addProduct(p);
       assertTrue(productService1.getProductList().contains(p));
       assertNotNull(addedProduct);
    }

    @Test
    public void testAddProductWithNoSellerInList() throws ProductException, SellerException {
        SellerService sellerService2 = new SellerService();
        sellerService2.addSeller(new Seller("Seller A"));
        ProductService productService2 = new ProductService(sellerService2);

        try{
            Product product = new Product(1,"test product", 50.0, "Seller Z");
            productService2.addProduct(product);
        }catch (ProductException | SellerException e){
            return;
        }
        throw new AssertionError("Expected ProductException but no exception was thrown");
    }

    @Test
    public void testAddProductWithNullValues() throws ProductException, SellerException {
        SellerService sellerService2 = new SellerService();
        sellerService2.addSeller(new Seller("Seller A"));
        ProductService productService2 = new ProductService(sellerService2);

        try{
            Product product = new Product(1,"", 50.0, "Seller A");
            productService2.addProduct(product);
        }catch (ProductException | SellerException e){
            return;
        }
        throw new AssertionError("Expected ProductException but no exception was thrown");
    }



        }





