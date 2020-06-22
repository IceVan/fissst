package pl.ice.fissst.demo.brand.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ice.fissst.demo.brand.service.BrandListingService;
import pl.ice.fissst.demo.model.entity.Brand;

import javax.validation.constraints.NotEmpty;

@Validated
@RestController
@RequestMapping("/brand")
public class BrandController {

    private BrandListingService brandListingService;

    @Autowired
    public BrandController(BrandListingService brandListingService) {
        this.brandListingService = brandListingService;
    }

    @GetMapping("/getBrand")
    @ApiOperation(value = "Get brand information.")
    public Brand getBrand(@RequestParam(name = "brandName", required = true) @ApiParam(name = "brandName", value = "Name of a brand we want to get info about.")  @NotEmpty(message = "Brand name should not be empty.") String name){
        return brandListingService.getBrandInfo(name);
    }
}
