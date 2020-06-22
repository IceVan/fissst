package pl.ice.fissst.demo.brand.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ice.fissst.demo.brand.dao.BrandDao;
import pl.ice.fissst.demo.brand.dao.ModelDao;
import pl.ice.fissst.demo.model.entity.Brand;
import pl.ice.fissst.demo.model.entity.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BrandListingServiceImplTest {

    static private Brand brand = new Brand();
    static private Model model = new Model();

    @Mock
    private ModelDao modelDao;

    @Mock
    private BrandDao brandDao;

    @InjectMocks
    private BrandListingService brandListingService = new BrandListingServiceImpl(modelDao, brandDao);

    @BeforeAll
    static void init(){
        brand.setName("Audi");
        brand.addModel(model);

        model.setName("TT");
    }

    @Test
    void getBrandInfoTestWithValue() {
        when(brandDao.getBrandByName("Audi")).thenReturn(brand);

        assertEquals("Audi", brandListingService.getBrandInfo("Audi").getName());
    }

    @Test
    void getBrandInfoTestWithoutValue_ShouldThrowIllegalArgumentException() {

        when(brandDao.getBrandByName(anyString())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> brandListingService.getBrandInfo(""));

        assertEquals("Brand name should not be empty.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> brandListingService.getBrandInfo(null));

        assertEquals("Brand name should not be empty.", exception.getMessage());
    }

    @Test
    void getModelByBrandAndModelNameWithValue() {
        when(modelDao.getModelByBrandAndModelName("Audi","TT")).thenReturn(model);

        assertEquals("Audi", brandListingService.getModelByBrandAndModelName("Audi","TT").getBrand().getName());
        assertEquals("TT", brandListingService.getModelByBrandAndModelName("Audi","TT").getName());
    }

    @Test
    void getModelByBrandAndModelNameWithoutValue_ShouldThrowIllegalArgumentException() {

        when(modelDao.getModelByBrandAndModelName(anyString(),anyString())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> brandListingService.getModelByBrandAndModelName("", null));

        assertEquals("Brand and model name should not be empty.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> brandListingService.getModelByBrandAndModelName(null, "test"));

        assertEquals("Brand and model name should not be empty.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> brandListingService.getModelByBrandAndModelName(null, null));

        assertEquals("Brand and model name should not be empty.", exception.getMessage());

        assertDoesNotThrow(() -> brandListingService.getModelByBrandAndModelName("test", "test"));
    }
}