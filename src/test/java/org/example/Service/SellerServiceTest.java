//package org.example.Service;
//
//import org.example.DAO.SellerDAO;
//import org.example.Exception.SellerException;
//import org.example.Model.Product;
//import org.example.Model.Seller;
//import org.example.Service.ProductService;
//import org.example.Service.SellerService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//public class SellerServiceTest {
//    SellerService sellerService;
//    SellerDAO sellerDAO;
//
//    ProductService productService;
//
//    @Before
//    public void setup() {
//        sellerService = new SellerService(sellerDAO);
//    }
//
//    @Test
//    public void testSellerServerEmptyAtStart() {
//        List<Seller> sellerList = sellerService.getAllSellers();
////        ensure that at the start, there are no Product
//        Assert.assertTrue(sellerList.isEmpty());
//    }
//
//
//    //Test GetAll Sellers and Add Sellers
//    @Test
//    public void testGetAllSellers() throws SellerException {
//
//        Seller sellerA = new Seller("Seller A");
//        Seller sellerB = new Seller("Seller B");
//        List<Seller> expectedSellers = List.of(sellerA, sellerB);
//        sellerService.addSeller(sellerA);
//        sellerService.addSeller(sellerB);
//        assertEquals(expectedSellers, sellerService.getAllSellers());
//    }
//
//    //Test Add Sellers
//    @Test
//    public void testAddSeller() throws SellerException {
//        Seller sellerA = new Seller("Seller A");
//        Seller sellerB = new Seller("Seller B");
//        sellerService.addSeller(sellerA);
//        sellerService.addSeller(sellerB);
//        assertEquals(2, sellerService.getAllSellers().size());
//    }
//
//    //Test Add Null Seller Name
//    @Test
//    public void testAddSellerWithNull() throws SellerException {
//        Seller sellerA = new Seller("");
//        try{
//            sellerService.addSeller(sellerA);
//        }catch (SellerException e){
//            return;
//        }
//        throw new AssertionError("Expected SellerException but no exception was thrown");
//    }
//
//
//    @Test
//    public void testAddSellerWithDuplicate() throws SellerException {
//        Seller sellerA = new Seller("Seller A");
//        Seller sellerB = new Seller("Seller A");
//        sellerService.addSeller(sellerA);
//        try{
//            sellerService.addSeller(sellerB);
//        }catch(SellerException e){
//            return;
//        }
//        throw new AssertionError("Error not caught FAIL TEST");
//    }
//
//    //Testing isValidSeller method Valid
//    @Test
//    public void testIsValidSeller() throws SellerException {
//        Seller sellerA = new Seller("Seller A");
//        Seller sellerB = new Seller("Seller B");
//        sellerService.addSeller(sellerA);
//        sellerService.addSeller(sellerB);
//        assertTrue(sellerService.isValidSeller("Seller A"));
//        assertTrue(sellerService.isValidSeller("Seller B"));
//
//    }
//
//
//}