package pl.ice.fissst.demo.sales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.ice.fissst.demo.sales.dto.DeleteSalesPitchesResponse;
import pl.ice.fissst.demo.sales.service.SalesOperationsService;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/sales")
public class SalesController {

    private SalesOperationsService salesOperationsService;

    @Autowired
    public SalesController(SalesOperationsService salesOperationsService) {
        this.salesOperationsService = salesOperationsService;
    }

    @CrossOrigin()
    @DeleteMapping("/deleteSalesPitchesFromPart")
    @ApiOperation(value = "Delete sales pitches form part with provided id..")
    public DeleteSalesPitchesResponse deleteSalesPitchesFromPart(@RequestParam(name = "partId", required = true) @ApiParam(name = "partId", value = "ID of the part we want to delete sales pitches from.") @Min(1) long partId){
        return new DeleteSalesPitchesResponse(partId,salesOperationsService.removeSalesPitchesFromPartWithId(partId));
    }

}
