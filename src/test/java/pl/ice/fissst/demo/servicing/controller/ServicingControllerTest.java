package pl.ice.fissst.demo.servicing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ice.fissst.demo.model.entity.ServiceAction;
import pl.ice.fissst.demo.servicing.dto.AddServiceActionDTO;
import pl.ice.fissst.demo.servicing.service.ServicingListingService;
import pl.ice.fissst.demo.servicing.service.ServicingOperationsService;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(ServicingController.class)
class ServicingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ServicingListingService servicingListingService;

    @MockBean
    private ServicingOperationsService servicingOperationsService;

    @Test
    void getActions_WithValidDates_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActions")
                .param("servicedFrom", "1900-01-01")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(servicingListingService, times(1)).getServiceActionInGivenPeriod(Date.valueOf("1900-01-01"), Date.valueOf("1901-01-01"));
    }

    @Test
    void getActions_NoDate_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActions")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingListingService, times(0)).getServiceActionInGivenPeriod(any(), any());
    }

    @Test
    void getActions_InvalidDate_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActions")
                .param("servicedFrom", "asdsad")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingListingService, times(0)).getServiceActionInGivenPeriod(any(), any());
    }




    @Test
    void getActionsForPart_WithValidDates_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActionsForPart")
                .param("partId", "999")
                .param("servicedFrom", "1900-01-01")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(servicingListingService, times(1)).getServiceActionForPartInGivenPeriod(999, Date.valueOf("1900-01-01"), Date.valueOf("1901-01-01"));
    }

    @Test
    void getActionsForPart_NoDate_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActionsForPart")
                .param("partId", "999")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingListingService, times(0)).getServiceActionForPartInGivenPeriod(anyLong(), any(), any());
    }

    @Test
    void getActionsForPart_InvalidDate_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActionsForPart")
                .param("partId", "999")
                .param("servicedFrom", "asdsad")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingListingService, times(0)).getServiceActionForPartInGivenPeriod(anyLong(), any(), any());
    }

    @Test
    void getActionsForPart_NoId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActionsForPart")
                .param("servicedFrom", "1900-01-01")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingListingService, times(0)).getServiceActionForPartInGivenPeriod(anyLong(),any(), any());
    }

    @Test
    void getActionsForPart_InvalidId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActionsForPart")
                .param("partId", "asdsd999")
                .param("servicedFrom", "1900-01-01")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingListingService, times(0)).getServiceActionForPartInGivenPeriod(anyLong(),any(), any());
    }

    @Test
    void getActionsForPart_NegativeId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/servicing/getActionsForPart")
                .param("partId", "-999")
                .param("servicedFrom", "1900-01-01")
                .param("servicedUntil", "1901-01-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingListingService, times(0)).getServiceActionForPartInGivenPeriod(anyLong(),any(), any());
    }





    @Test
    void addServiceAction_ValidRequestBody_ShouldReturn201() throws Exception {
        Date dateFrom = Date.valueOf("1900-01-01");
        Date dateTo = Date.valueOf("1901-01-01");

        AddServiceActionDTO addServiceActionDTO = new AddServiceActionDTO();
        addServiceActionDTO.setName("test name");
        addServiceActionDTO.setPartId(99);
        addServiceActionDTO.setServicedFrom(dateFrom);
        addServiceActionDTO.setServicedUntil(dateTo);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/servicing/addServiceAction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(addServiceActionDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        ArgumentCaptor<AddServiceActionDTO> serviceActionArgumentCaptor = ArgumentCaptor.forClass(AddServiceActionDTO.class);
        verify(servicingOperationsService, times(1)).createAndAddActionToPart(serviceActionArgumentCaptor.capture());
        assertEquals("test name", serviceActionArgumentCaptor.getValue().getName());
        assertEquals(99, serviceActionArgumentCaptor.getValue().getPartId());
        assertEquals(dateFrom.toString(), serviceActionArgumentCaptor.getValue().getServicedFrom().toString());
        assertEquals(dateTo.toString(), serviceActionArgumentCaptor.getValue().getServicedUntil().toString());
    }

    @Test
    void addServiceAction_ValidRequestBodyNoEndDate_ShouldReturn201() throws Exception {
        Date dateFrom = Date.valueOf("1900-01-01");

        AddServiceActionDTO addServiceActionDTO = new AddServiceActionDTO();
        addServiceActionDTO.setName("test name");
        addServiceActionDTO.setPartId(99);
        addServiceActionDTO.setServicedFrom(dateFrom);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/servicing/addServiceAction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(addServiceActionDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        ArgumentCaptor<AddServiceActionDTO> serviceActionArgumentCaptor = ArgumentCaptor.forClass(AddServiceActionDTO.class);
        verify(servicingOperationsService, times(1)).createAndAddActionToPart(serviceActionArgumentCaptor.capture());
        assertEquals("test name", serviceActionArgumentCaptor.getValue().getName());
        assertEquals(99, serviceActionArgumentCaptor.getValue().getPartId());
        assertEquals(dateFrom.toString(), serviceActionArgumentCaptor.getValue().getServicedFrom().toString());
        assertEquals(null, serviceActionArgumentCaptor.getValue().getServicedUntil());
    }

    @Test
    void addServiceAction_NoId_ShouldReturn400() throws Exception {
        Date dateFrom = Date.valueOf("1900-01-01");
        Date dateTo = Date.valueOf("1901-01-01");

        AddServiceActionDTO addServiceActionDTO = new AddServiceActionDTO();
        addServiceActionDTO.setName("test name");
        addServiceActionDTO.setServicedFrom(dateFrom);
        addServiceActionDTO.setServicedUntil(dateTo);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/servicing/addServiceAction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(addServiceActionDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingOperationsService, times(0)).createAndAddActionToPart(any(AddServiceActionDTO.class));
    }

    @Test
    void addServiceAction_NegativeId_ShouldReturn400() throws Exception {
        Date dateFrom = Date.valueOf("1900-01-01");
        Date dateTo = Date.valueOf("1901-01-01");

        AddServiceActionDTO addServiceActionDTO = new AddServiceActionDTO();
        addServiceActionDTO.setName("test name");
        addServiceActionDTO.setPartId(-48);
        addServiceActionDTO.setServicedFrom(dateFrom);
        addServiceActionDTO.setServicedUntil(dateTo);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/servicing/addServiceAction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(addServiceActionDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingOperationsService, times(0)).createAndAddActionToPart(any(AddServiceActionDTO.class));
    }

    @Test
    void addServiceAction_EmptyName_ShouldReturn400() throws Exception {
        Date dateFrom = Date.valueOf("1900-01-01");
        Date dateTo = Date.valueOf("1901-01-01");

        AddServiceActionDTO addServiceActionDTO = new AddServiceActionDTO();
        addServiceActionDTO.setName("");
        addServiceActionDTO.setPartId(99);
        addServiceActionDTO.setServicedFrom(dateFrom);
        addServiceActionDTO.setServicedUntil(dateTo);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/servicing/addServiceAction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(addServiceActionDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingOperationsService, times(0)).createAndAddActionToPart(any(AddServiceActionDTO.class));
    }

    @Test
    void addServiceAction_NoStartDate_ShouldReturn400() throws Exception {
        Date dateTo = Date.valueOf("1901-01-01");

        AddServiceActionDTO addServiceActionDTO = new AddServiceActionDTO();
        addServiceActionDTO.setName("");
        addServiceActionDTO.setPartId(99);
        addServiceActionDTO.setServicedUntil(dateTo);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/servicing/addServiceAction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(addServiceActionDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(servicingOperationsService, times(0)).createAndAddActionToPart(any(AddServiceActionDTO.class));
    }
}