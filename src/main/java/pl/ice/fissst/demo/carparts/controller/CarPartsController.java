package pl.ice.fissst.demo.carparts.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.ice.fissst.demo.carparts.service.PartsListingService;
import pl.ice.fissst.demo.carparts.service.PartsOperationsService;
import pl.ice.fissst.demo.model.entity.PartInfo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Log
@Validated
@RestController
@RequestMapping("/parts")
public class CarPartsController {

    private PartsListingService partsListingService;
    private PartsOperationsService partsOperationsService;

    @Autowired
    public CarPartsController(PartsListingService partsListingService, PartsOperationsService partsOperationsService) {
        this.partsListingService = partsListingService;
        this.partsOperationsService = partsOperationsService;
    }

    @GetMapping("/getParts")
    @ApiOperation(value = "Get all parts for brand and model with filter based on part name or description. ")
    public List<PartInfo> getAllPartsForBrandAndModel(@RequestParam(name = "brandName") @ApiParam(name = "brandName", value = "Brand name.") @NotBlank(message = "Brand name cannot be empty.") String brand,
                                                      @RequestParam(name = "modelName") @ApiParam(name = "modelName", value = "Model of brand.") @NotBlank(message = "Model name cannot be empty.") String model,
                                                      @RequestParam(name = "partName", defaultValue = "") @ApiParam(name = "partName", value = "Name of part we want to filter. Anything not containing provided string will be filtered out.") String partName,
                                                      @RequestParam(name = "partDescription", defaultValue = "") @ApiParam(name = "partDescription", value = "Partial description of part we want to get. Anything not containing provided string will be filtered out.") String partDescription){

        if(partName.isEmpty() && partDescription.isEmpty()) return partsListingService.getAllPartsForBrandAndModel(brand, model);
        return partsListingService.getAllPartsForBrandAndModelFilteredByNameAndDescription(brand,model,partName,partDescription);
    }

    @PatchMapping("/changeDescription")
    @ApiOperation(value = "Change description of part with provided ID.")
    public PartInfo changePartInfoDescription(@RequestParam(name = "partId", required = true) @ApiParam(name = "partId", value = "ID of a part for which we want to change description.") @Min(value = 1, message = "PartId must be positive.") long partId,
                                              @RequestParam(name = "description", required = true) @ApiParam(name = "description", value = "Description to set for given part.") @NotBlank(message = "Description cannot be empty.") String description){
        return partsOperationsService.changePartDescriptionWithId(partId, description);
    }
}
