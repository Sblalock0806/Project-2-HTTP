package org.example.Service;
import org.example.DAO.SellerDAO;
import org.example.Exception.SellerException;
import org.example.Model.Seller;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class SellerServiceTest {
    private SellerService sellerService;
    private SellerDAO sellerDAO;


    @Before
    public void setup() {
        sellerDAO = mock(SellerDAO.class);
        sellerService = new SellerService(sellerDAO);
    }


    //Test GetAll Sellers and Add Sellers
    @Test
    public void testGetAllSellers() throws SellerException {
        List<Seller> expectedSellers = new ArrayList<>();
        expectedSellers.add(new Seller(1, "Seller A"));
        expectedSellers.add(new Seller(2, "Seller B"));
        when(sellerDAO.getAllSellers()).thenReturn(expectedSellers);

        List<Seller> actualSeller = sellerService.getAllSellers();
        System.out.println("Expected Sellers: " + expectedSellers);
        System.out.println("Actual Sellers: " + actualSeller);
        assertEquals(expectedSellers.size(), actualSeller.size());
    }


    //    //Test Add Sellers
    @Test
    public void testAddSeller() throws SellerException {
        Seller sellerA = new Seller(1, "Seller A");
        when(sellerDAO.getAllSellers()).thenReturn(new ArrayList<>());

        sellerService.addSeller(sellerA);
        verify(sellerDAO, times(1)).insertSeller(sellerA);
    }

    @Test (expected = SellerException.class)
    public void testAddSellerWithDuplicate() throws SellerException {
        Seller sellerA = new Seller(1, "Seller A");
        List<Seller> existingSellers = new ArrayList<>();
        existingSellers.add(sellerA);
        when(sellerDAO.getAllSellers()).thenReturn(existingSellers);

        sellerService.addSeller(sellerA);

    }
    @Test (expected = SellerException.class)
    public void testAddSellerWithNullId() throws SellerException {
        Seller sellerA = new Seller(0, "Seller A");

        sellerService.addSeller(sellerA);

    }

    @Test (expected = SellerException.class)
    public void testAddSellerWithNullName() throws SellerException {
        Seller sellerA = new Seller(1, "");

        sellerService.addSeller(sellerA);
    }

    @Test
    public void testIsValidSeller() throws SellerException{
        long existingSellerId = 1;
        List<Seller> existingSeller = new ArrayList<>();
        existingSeller.add(new Seller(existingSellerId, "SellerTest"));
        when(sellerDAO.getAllSellers()).thenReturn(existingSeller);

        boolean result = sellerService.isValidSeller(existingSellerId);
        assertTrue(result);
    }

    @Test
    public void testIsInValidSeller() throws SellerException{
        long invalidSellerId = 89;
        List<Seller> existingSeller = new ArrayList<>();
        existingSeller.add(new Seller(1, "SellerTest"));
        when(sellerDAO.getAllSellers()).thenReturn(existingSeller);

        boolean result = sellerService.isValidSeller(invalidSellerId);
        assertFalse(result);
    }

}


