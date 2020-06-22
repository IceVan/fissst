package pl.ice.fissst.demo.brand.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ice.fissst.demo.brand.service.BrandListingService;

import static org.mockito.Mockito.*;

@WebMvcTest(BrandController.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandListingService brandListingService;

    @Test
    void getBrandWithValidParams_ShouldExecuteGetBrandInfo_AndReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/brand/getBrand").param("brandName", "Audi"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(brandListingService, times(1)).getBrandInfo("Audi");
    }

    @Test
    void getBrandWithNoParam_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/brand/getBrand"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(brandListingService, times(0)).getBrandInfo(anyString());
    }

    @Test
    void getBrandWithInvalidParams_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/brand/getBrand").param("brandName", ""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(brandListingService, times(0)).getBrandInfo(anyString());
    }
}