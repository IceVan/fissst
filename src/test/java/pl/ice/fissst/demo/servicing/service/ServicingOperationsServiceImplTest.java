package pl.ice.fissst.demo.servicing.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ice.fissst.demo.carparts.service.PartsListingService;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.model.entity.ServiceAction;
import pl.ice.fissst.demo.servicing.dto.AddServiceActionDTO;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServicingOperationsServiceImplTest {

    private static Part part = new Part();

    @Mock
    private PartsListingService partsListingService;

    @InjectMocks
    private ServicingOperationsServiceImpl servicingOperationsService = new ServicingOperationsServiceImpl(partsListingService);

    @BeforeAll
    static void init(){
        part.setId(99);
    }

    @Test
    void createAndAddActionToPart_ShouldSucceed() {
        AddServiceActionDTO serviceActionDTO = new AddServiceActionDTO();
        serviceActionDTO.setPartId(99);
        serviceActionDTO.setName("test service x");
        serviceActionDTO.setServicedFrom(Date.valueOf("1999-01-01"));
        serviceActionDTO.setServicedUntil(Date.valueOf("2000-01-01"));

        when(partsListingService.getPartForId(99)).thenReturn(part);

        ServiceAction sa = servicingOperationsService.createAndAddActionToPart(serviceActionDTO);

        assertEquals(99, sa.getPart().getId());
        assertEquals("test service x", sa.getName());
        assertEquals(Date.valueOf("1999-01-01").toString(), sa.getServicedSince().toString());
        assertEquals(Date.valueOf("2000-01-01").toString(), sa.getServicedUntil().toString());
    }

    @Test
    void createAndAddActionToPart_ShouldThrowIllegalArgumentException() {
        when(partsListingService.getPartForId(99)).thenReturn(part);

        AddServiceActionDTO serviceActionDTO = new AddServiceActionDTO();
        serviceActionDTO.setPartId(99);
        serviceActionDTO.setName("test service x");
        serviceActionDTO.setServicedFrom(Date.valueOf("2001-01-01"));
        serviceActionDTO.setServicedUntil(Date.valueOf("2000-01-01"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> servicingOperationsService.createAndAddActionToPart(serviceActionDTO));

        assertEquals("Starting date should not be after end date.", exception.getMessage());
    }
}