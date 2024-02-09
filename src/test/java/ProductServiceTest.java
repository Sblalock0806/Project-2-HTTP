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

    SellerService sellerService;
    ProductService productService;

    @Before
    public void setup() {
        productService = new ProductService(sellerService);
        SellerService sellerService = new SellerService();


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
        Product product = new Product(1,"test product", 50.0, "Seller A");
        Product testProduct = productService.addProduct(product);
//        ensure that at the start, there are no Product
        assertNotNull(testProduct.getProductID());
        assertEquals("test product", testProduct.getProductName());
        assertEquals("50.0", testProduct.getPrice());
        assertEquals("Seller A", testProduct.getSellerName());
        List<Product> productList = productService.getProductList();
        assertTrue(productList.contains(testProduct));
    }
    //Test UpdateProduct


}
