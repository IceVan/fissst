package pl.ice.fissst.demo.carparts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ice.fissst.demo.carparts.service.PartsListingService;
import pl.ice.fissst.demo.carparts.service.PartsOperationsService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(CarPartsController.class)
class CarPartsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartsListingService partsListingService;

    @MockBean
    private PartsOperationsService partsOperationsService;

    @Test
    void getAllPartsForBrandAndModelWithFilter_ProperlyValidated_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "Audi")
                .param("modelName", "TT")
                .param("partName", "Engine")
                .param("partDescription", "en"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(partsListingService, times(1)).getAllPartsForBrandAndModelFilteredByNameAndDescription("Audi", "TT", "Engine", "en");
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }

    @Test
    void getAllPartsForBrandAndModelWithFilter_OnlyPartNameFilterCriteria_ProperlyValidated_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "Audi")
                .param("modelName", "TT")
                .param("partName", "Engine"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(partsListingService, times(1)).getAllPartsForBrandAndModelFilteredByNameAndDescription("Audi", "TT", "Engine", "");
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }

    @Test
    void getAllPartsForBrandAndModelWithFilter_OnlyPartDescriptionFilterCriteria_ProperlyValidated_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "Audi")
                .param("modelName", "TT")
                .param("partDescription", "en"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(partsListingService, times(1)).getAllPartsForBrandAndModelFilteredByNameAndDescription("Audi", "TT", "", "en");
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }

    @Test
    void getAllPartsForBrandAndModelWithFilter_NoModel_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "Audi")
                .param("partName", "Engine")
                .param("partDescription", "en"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsListingService, times(0)).getAllPartsForBrandAndModelFilteredByNameAndDescription(anyString(), anyString(), anyString(), anyString());
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }

    @Test
    void getAllPartsForBrandAndModelWithFilter_NoModelEmptyBrand_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "")
                .param("partName", "Engine")
                .param("partDescription", "en"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsListingService, times(0)).getAllPartsForBrandAndModelFilteredByNameAndDescription(anyString(), anyString(), anyString(), anyString());
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }






    @Test
    void getAllPartsForBrandAndModelWithOutFilter_ProperlyValidated_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "Audi")
                .param("modelName", "TT"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(partsListingService, times(0)).getAllPartsForBrandAndModelFilteredByNameAndDescription(anyString(), anyString(),anyString(),anyString());
        verify(partsListingService, times(1)).getAllPartsForBrandAndModel("Audi", "TT");
    }

    @Test
    void getAllPartsForBrandAndModelWithOutFilter_BrandEmpty_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "")
                .param("modelName", "TT"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsListingService, times(0)).getAllPartsForBrandAndModelFilteredByNameAndDescription(anyString(), anyString(), anyString(), anyString());
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }

    @Test
    void getAllPartsForBrandAndModelWithOutFilter_ModelEmpty_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "Audi")
                .param("modelName", ""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsListingService, times(0)).getAllPartsForBrandAndModelFilteredByNameAndDescription(anyString(), anyString(), anyString(), anyString());
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }

    @Test
    void getAllPartsForBrandAndModelWithOutFilter_NoBrand_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("modelName", "TT"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsListingService, times(0)).getAllPartsForBrandAndModelFilteredByNameAndDescription(anyString(), anyString(), anyString(), anyString());
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }

    @Test
    void getAllPartsForBrandAndModelWithOutFilter_NoModel_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/parts/getParts")
                .param("brandName", "Audi"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsListingService, times(0)).getAllPartsForBrandAndModelFilteredByNameAndDescription(anyString(), anyString(), anyString(), anyString());
        verify(partsListingService, times(0)).getAllPartsForBrandAndModel(anyString(), anyString());
    }







    @Test
    void changePartInfoDescription_ProperlyValidated_ShouldReturn200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/parts/changeDescription")
                .param("partId", "25")
                .param("description", "test description"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(partsOperationsService, times(1)).changePartDescriptionWithId(25, "test description");
    }

    @Test
    void changePartInfoDescription_IdNotPositive_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/parts/changeDescription")
                .param("partId", "-4")
                .param("description", "test description"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsOperationsService, times(0)).changePartDescriptionWithId(anyInt(), anyString());
    }

    @Test
    void changePartInfoDescription_IdNotNumber_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/parts/changeDescription")
                .param("partId", "a-4")
                .param("description", "test description"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsOperationsService, times(0)).changePartDescriptionWithId(anyLong(), anyString());
    }

    @Test
    void changePartInfoDescription_EmptyDescription_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/parts/changeDescription")
                .param("partId", "25")
                .param("description", ""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsOperationsService, times(0)).changePartDescriptionWithId(anyInt(), anyString());
    }

    @Test
    void changePartInfoDescription_NoId_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/parts/changeDescription")
                .param("description", "test description"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsOperationsService, times(0)).changePartDescriptionWithId(anyInt(), anyString());
    }

    @Test
    void changePartInfoDescription_NoDescription_ShouldReturn400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/parts/changeDescription")
                .param("partId", "25"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(partsOperationsService, times(0)).changePartDescriptionWithId(anyInt(), anyString());
    }
}