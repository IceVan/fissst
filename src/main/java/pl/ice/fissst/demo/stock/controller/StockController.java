package pl.ice.fissst.demo.stock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.stock.service.StockListingService;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/stock")
public class StockController {

    private StockListingService stockListingService;

    @Autowired
    public StockController(StockListingService stockListingService) {
        this.stockListingService = stockListingService;
    }

    @CrossOrigin()
    @GetMapping("/checkPart")
    @ApiOperation(value = "Check if part with provided id is in stock.")
    public Part checkPartAvailability(@RequestParam(name = "partId", required = true) @ApiParam(name = "partId", value = "ID of the part we want to check stock for.") @Min(1) long partId){
        return stockListingService.checkPartInStock(partId);
    }
}
