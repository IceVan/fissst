package pl.ice.fissst.demo.carparts.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ice.fissst.demo.brand.service.BrandListingService;
import pl.ice.fissst.demo.carparts.dao.PartDao;
import pl.ice.fissst.demo.carparts.dao.PartDaoImpl;
import pl.ice.fissst.demo.carparts.dao.PartInfoDao;
import pl.ice.fissst.demo.model.entity.Model;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.model.entity.PartInfo;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PartsListingServiceImplTest {

    private static Model model = new Model();

    private static PartInfo partInfo1 = new PartInfo();

    private static PartInfo partInfo2 = new PartInfo();

    private static Part part1 = new Part();

    private static Part part2 = new Part();

    @Mock
    private BrandListingService brandListingService;

    @Mock
    private PartInfoDao partInfoDao;

    @Mock
    private PartDao partDao;

    @InjectMocks
    private PartsListingServiceImpl partsListingService = new PartsListingServiceImpl(brandListingService, partInfoDao, partDao);

    @BeforeAll
    static void init(){
        model.setName("TT");

        part1.setId(11);
        part2.setId(12);

        partInfo1.setName("engine");
        partInfo2.setName("wheel");

        partInfo1.addPart(part1);
        partInfo2.addPart(part2);

        model.addPartInfo(partInfo1);
        model.addPartInfo(partInfo2);
    }

    @Test
    void getAllPartsForBrandAndModel_ShouldPass() {
        when(brandListingService.getModelByBrandAndModelName("Audi", "TT")).thenReturn(model);
        when(partInfoDao.getAllPartsForModel(model)).thenReturn(model.getPartTypes());

        assertEquals(model.getPartTypes(), partsListingService.getAllPartsForBrandAndModel("Audi", "TT"));

        verify(partInfoDao, times(1)).getAllPartsForModel(model);
    }



    @Test
    void getAllPartsForBrandAndModelFilteredByNameAndDescription_ShouldPass() {
        when(brandListingService.getModelByBrandAndModelName("Audi", "TT")).thenReturn(model);
        when(partInfoDao.getAllPartsForModelWithFilter(model, "engine", "")).thenReturn(model.getPartTypes().subList(0,1));

        assertEquals("engine", partsListingService.getAllPartsForBrandAndModelFilteredByNameAndDescription("Audi", "TT", "engine", "").get(0).getName());

        verify(partInfoDao, times(1)).getAllPartsForModelWithFilter(model, "engine", "");
    }

    @Test
    void getPartForId_ShouldPass() {
        when(partDao.getPartByID(11)).thenReturn(part1);
        assertEquals(11, partsListingService.getPartForId(11).getId());
    }

    @Test
    void getPartForId_ShouldThrowIllegalArgumentException(){
        when(partDao.getPartByID(999)).thenThrow(new NoResultException());
        when(partDao.getPartByID(998)).thenThrow(new PersistenceException("test"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> partsListingService.getPartForId(999));

        assertEquals(exception.getMessage(), "No part was found for ID: 999");

        exception = assertThrows(IllegalArgumentException.class, () -> partsListingService.getPartForId(998));

        assertEquals(exception.getMessage(), "Database problem: test");
    }
}