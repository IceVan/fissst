package pl.ice.fissst.demo.servicing.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ice.fissst.demo.servicing.dao.ServiceActionDao;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServicingListingServiceImplTest {

    @Mock
    private ServiceActionDao serviceActionDao;

    @InjectMocks
    private ServicingListingService servicingListingService = new ServicingListingServiceImpl(serviceActionDao);

    @Test
    void getServiceActionInGivenPeriod_GoodDatesOrder_ShouldBeSuccessful() {
        when(serviceActionDao.getServiceActionsInPeriod(Date.valueOf("1992-01-01"), Date.valueOf("1993-01-01"))).thenReturn(new ArrayList<>());
        servicingListingService.getServiceActionInGivenPeriod(Date.valueOf("1992-01-01"), Date.valueOf("1993-01-01"));

        verify(serviceActionDao, times(1)).getServiceActionsInPeriod(Date.valueOf("1992-01-01"), Date.valueOf("1993-01-01"));
    }

    @Test
    void getServiceActionInGivenPeriod_WrongDateOrder_ShouldThrowIllegalArgumentException() {
        when(serviceActionDao.getServiceActionsInPeriod(Date.valueOf("1993-01-01"), Date.valueOf("1992-01-01"))).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> servicingListingService.getServiceActionInGivenPeriod(Date.valueOf("1993-01-01"), Date.valueOf("1992-01-01")));

        assertEquals("Starting date should not be after end date.", exception.getMessage());
        verify(serviceActionDao, times(0)).getServiceActionsInPeriod(any(), any());
    }

    @Test
    void getServiceActionForPartInGivenPeriod_GoodDatesOrder_ShouldBeSuccessful() {
        when(serviceActionDao.getServiceActionsInPeriodForPart(99, Date.valueOf("1992-01-01"), Date.valueOf("1993-01-01"))).thenReturn(new ArrayList<>());
        servicingListingService.getServiceActionForPartInGivenPeriod(99, Date.valueOf("1992-01-01"), Date.valueOf("1993-01-01"));

        verify(serviceActionDao, times(1)).getServiceActionsInPeriodForPart(99, Date.valueOf("1992-01-01"), Date.valueOf("1993-01-01"));
    }

    @Test
    void getServiceActionForPartInGivenPeriod_WrongDateOrder_ShouldThrowIllegalArgumentException() {
        when(serviceActionDao.getServiceActionsInPeriodForPart(99, Date.valueOf("1993-01-01"), Date.valueOf("1992-01-01"))).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> servicingListingService.getServiceActionForPartInGivenPeriod(99, Date.valueOf("1993-01-01"), Date.valueOf("1992-01-01")));

        assertEquals("Starting date should not be after end date.", exception.getMessage());
        verify(serviceActionDao, times(0)).getServiceActionsInPeriodForPart(anyLong(), any(), any());
    }
}