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
public class SellerServiceTest {
    SellerService sellerService;

    @Before
    public void setup() {
        sellerService = new SellerService();
    }

    @Test
    public void testSellerServerEmptyAtStart() {
        List<Seller> sellerList = sellerService.getAllSellers();
//        ensure that at the start, there are no Product
        Assert.assertTrue(sellerList.isEmpty());
    }

    @Test
    public void testAddSeller() throws SellerException {
        Seller seller = sellerService.addSeller();
        assertEquals(1, sellerService.getAllSellers().size());
    }
}
