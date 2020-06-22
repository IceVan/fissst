package pl.ice.fissst.demo.stock.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ice.fissst.demo.stock.service.StockListingService;

import static org.mockito.Mockito.*;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StockListingService stockListingService;

    @Test
    void checkPartInStock_ValidId_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/checkPart")
                .param("partId", "99"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(stockListingService, times(1)).checkPartInStock(99);
    }

    @Test
    void checkPartInStock_InvalidId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/checkPart")
                .param("partId", "asd99"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(stockListingService, times(0)).checkPartInStock(anyLong());
    }

    @Test
    void checkPartInStock_NoId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/checkPart"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(stockListingService, times(0)).checkPartInStock(anyLong());
    }

    @Test
    void checkPartInStock_NegativeId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/checkPart")
                .param("partId", "-99"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(stockListingService, times(0)).checkPartInStock(anyLong());
    }
}