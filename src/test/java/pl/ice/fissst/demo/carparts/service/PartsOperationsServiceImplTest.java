package pl.ice.fissst.demo.carparts.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ice.fissst.demo.carparts.dao.PartDao;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.model.entity.PartInfo;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PartsOperationsServiceImplTest {

    static private PartInfo partInfo;
    static private Part part;

    @Mock
    private PartDao partDao;

    @InjectMocks
    private PartsOperationsService partsOperationsService = new PartsOperationsServiceImpl(partDao);

    @BeforeAll
    static void init(){
        partInfo = new PartInfo();
        partInfo.setName("Test Part");
        partInfo.setDescription("Test");

        part = new Part();
        partInfo.addPart(part);
    }

    @Test
    void changePartDescriptionWithId_ShouldChangeDescription() {
        when(partDao.getPartByID(66)).thenReturn(part);

        PartInfo testInfo = partsOperationsService.changePartDescriptionWithId(66, "Test done");

        assertEquals(testInfo.getDescription(), "Test done");
        assertEquals(testInfo.getName(), "Test Part");
    }

    @Test
    void changePartDescriptionWithId_ShouldThrowIllegalArgumentException() {
        when(partDao.getPartByID(999)).thenThrow(new NoResultException());
        when(partDao.getPartByID(998)).thenThrow(new PersistenceException("test"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> partsOperationsService.changePartDescriptionWithId(999, "test"));

        assertEquals("No part was found for ID: 999", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> partsOperationsService.changePartDescriptionWithId(998, "test"));

        assertEquals("Database problem: test", exception.getMessage());
    }
}