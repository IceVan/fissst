package pl.ice.fissst.demo.stock.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ice.fissst.demo.carparts.service.PartsListingService;
import pl.ice.fissst.demo.model.entity.Part;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class StockListingServiceImplTest {

    private static Part part = new Part();

    @Mock
    private PartsListingService partsListingService;

    @InjectMocks
    private StockListingService stockListingService = new StockListingServiceImpl(partsListingService);

    @BeforeAll
    static void init(){
        part.setId(99);
        part.setInStock(true);
    }

    @Test
    void checkPartAvailability_ShouldSucceed() {
        when(partsListingService.getPartForId(99)).thenReturn(part);

        Part p = stockListingService.checkPartInStock(99);

        assertEquals(99, p.getId());
        assertTrue(p.isInStock());
    }
}