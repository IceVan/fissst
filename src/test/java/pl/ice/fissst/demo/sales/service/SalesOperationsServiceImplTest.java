package pl.ice.fissst.demo.sales.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ice.fissst.demo.carparts.service.PartsListingService;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.sales.dao.SalesPitchDao;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class SalesOperationsServiceImplTest {

    static private Part part;

    @Mock
    private SalesPitchDao salesPitchDao;

    @Mock
    private PartsListingService partsListingService;

    @InjectMocks
    private SalesOperationsService salesOperationsService = new SalesOperationsServiceImpl(salesPitchDao, partsListingService);

    @BeforeAll
    static void init(){
        part = new Part();
        part.setId(1);
    }

    @Test
    void removeSalesPitchesFromPartWithId() {
        when(partsListingService.getPartForId(99)).thenReturn(part);

        assertTrue(salesOperationsService.removeSalesPitchesFromPartWithId(99));

        verify(salesPitchDao, times(1)).removeSalesPitchesFromPart(isA(Part.class));
    }
}