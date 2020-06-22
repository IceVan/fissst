package pl.ice.fissst.demo.sales.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ice.fissst.demo.sales.service.SalesOperationsService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(SalesController.class)
class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesOperationsService salesOperationsService;

    @Test
    void deleteSalesPitchesFromPart_ProperlyValidated_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/sales/deleteSalesPitchesFromPart")
                .param("partId", "25"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(salesOperationsService, times(1)).removeSalesPitchesFromPartWithId(25);
    }

    @Test
    void deleteSalesPitchesFromPart_NoId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/sales/deleteSalesPitchesFromPart"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(salesOperationsService, times(0)).removeSalesPitchesFromPartWithId(anyLong());
    }

    @Test
    void deleteSalesPitchesFromPart_NegativeId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/sales/deleteSalesPitchesFromPart")
                .param("partId", "-25"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(salesOperationsService, times(0)).removeSalesPitchesFromPartWithId(anyLong());
    }

    @Test
    void deleteSalesPitchesFromPart_IdNAN_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/sales/deleteSalesPitchesFromPart")
                .param("partId", "asd-25"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(salesOperationsService, times(0)).removeSalesPitchesFromPartWithId(anyLong());
    }
}