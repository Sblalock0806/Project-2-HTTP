package org.example.Service;
import org.example.DAO.ProductDao;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class ProductServiceTest {

    ProductService productService;
    ProductDao productDao;
    SellerService sellerService;


    @Before
    public void setup() {

        productDao = mock(ProductDao.class);
        sellerService = mock(SellerService.class);
        productService = new ProductService(sellerService, productDao);

    }

    @Test
    public void testGetAllProducts() {
        List<Product> existingProducts = new ArrayList<>();
        existingProducts.add(new Product(1, "A", 10.0, 1));
        existingProducts.add(new Product(1, "A", 10.0, 1));
        when(productDao.getAllProducts()).thenReturn(existingProducts);

        List<Product> result = productService.getProductList();
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void testGetEmptyProductsList() {
        when(productDao.getAllProducts()).thenReturn(new ArrayList<>());
        List<Product> result = productService.getProductList();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAddProductWhenValidProduct() throws ProductException, SellerException {
        Product newProduct = new Product(1,"Product A", 10.0, 1);
        when(sellerService.isValidSeller(newProduct.getSellerID())).thenReturn(true);
        Product result = productService.addProduct(newProduct);
        assertNotNull(result);
        assertEquals(newProduct.getProductName(), result.getProductName());
    }

    @Test (expected = ProductException.class)
    public void testAddProductWithInvalidSeller() throws ProductException, SellerException {
        Product invalidProduct = new Product(1,"Product A", 10.0, 0);
        when(sellerService.isValidSeller(invalidProduct.getSellerID())).thenReturn(false);
        productService.addProduct(invalidProduct);
    }

    @Test
    public void testSearchByProductId() throws ProductException,  SQLException {
        long validProductId = 1;
        Product expectedProduct = new Product(validProductId,"product a",10.0,1);
        when(productDao.getProductById(validProductId)).thenReturn(expectedProduct);

        Product result = productService.getProductByID(validProductId);

        assertNotNull(result);
        assertEquals(expectedProduct,result);
    }

    @Test(expected = ProductException.class)
    public void testSearchByProductIdInvalidId() throws ProductException,  SQLException {
        long inValidProductId = 2;
        when(productDao.getProductById(inValidProductId)).thenReturn(null);

        productService.getProductByID(inValidProductId);
    }

    @Test
    public void testDeleteByProductId() throws ProductException,SQLException{
        long validProductId = 1;
        Product expectedProduct = new Product(validProductId,"product a",10.0,1);
        when(productDao.getProductById(validProductId)).thenReturn(expectedProduct);
        Product result = productService.deleteProduct(expectedProduct);
        assertNotNull(result);
        assertEquals(expectedProduct,result);
// verify is used to verify that certain interactiosn with mck objects have occurred during the test execution
        verify(productDao).deleteProductById(validProductId);
    }

    @Test(expected = ProductException.class)
    public void testDeleteByNotExistingProductId() throws ProductException,SQLException{
        long inValidProductId = 2;
        when(productDao.getProductById(inValidProductId)).thenReturn(null);

        productService.deleteProduct(new Product(inValidProductId,"Test",10.0,2));
    }

    @Test
    public void testUpdateProduct() throws  ProductException, SellerException{
        long id = 123;
        Product product = new Product(id,"Updated Product", 10.0,456);

        when(sellerService.isValidSeller(product.getSellerID())).thenReturn(true);
        when(productDao.updateProductById(product,id)).thenReturn(product);
        productService.updateProduct(product,id);
        verify(sellerService).isValidSeller(product.getSellerID());
        verify(productDao).updateProductById(product,id);

    }
}