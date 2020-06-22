package pl.ice.fissst.demo.servicing.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.ice.fissst.demo.model.entity.ServiceAction;
import pl.ice.fissst.demo.servicing.dto.AddServiceActionDTO;
import pl.ice.fissst.demo.servicing.service.ServicingListingService;
import pl.ice.fissst.demo.servicing.service.ServicingOperationsService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.util.List;

@Log
@Validated
@RestController
@RequestMapping("/servicing")
public class ServicingController {

    private ServicingListingService servicingListingService;
    private ServicingOperationsService servicingOperationsService;

    @Autowired
    public ServicingController(ServicingListingService servicingListingService, ServicingOperationsService servicingOperationsService) {
        this.servicingListingService = servicingListingService;
        this.servicingOperationsService = servicingOperationsService;
    }

    @GetMapping("/getActions")
    @ApiOperation(value = "Get all servicing actions between dates.")
    public List<ServiceAction> getActions(@RequestParam(name = "servicedFrom", required = true) @ApiParam(name = "servicedFrom", value = "Date from when we want to list actions.") Date dateFrom,
                                          @RequestParam(name = "servicedUntil", required = true) @ApiParam(name = "servicedUntil", value = "Date until which we want to list actions.") Date dateUntil){
        return servicingListingService.getServiceActionInGivenPeriod(dateFrom, dateUntil);
    }

    @GetMapping("/getActionsForPart")
    @ApiOperation(value = "Get all servicing actions between dates for given part id.")
    public List<ServiceAction> getActionsForPart(@RequestParam(name = "partId", required = true) @Min(1) @ApiParam(name = "partId", value = "Id of the part we want to check service actions for.") long partId,
                                                 @RequestParam(name = "servicedFrom") @ApiParam(name = "servicedFrom", value = "Date from when we want to list actions.") Date dateFrom,
                                                 @RequestParam(name = "servicedUntil") @ApiParam(name = "servicedUntil", value = "Date until which we want to list actions.") Date dateUntil){
        return servicingListingService.getServiceActionForPartInGivenPeriod(partId, dateFrom, dateUntil);
    }

    @PostMapping("/addServiceAction")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add service action fot part.")
    public ServiceAction addServiceAction(@Valid @RequestBody @ApiParam(name = "serviceAction", value = "Service action object.") AddServiceActionDTO serviceAction){
//        return servicingOperationsService.createAndAddActionToPart(serviceAction.getPartId(), serviceAction.getName(), serviceAction.getServicedFrom(), serviceAction.getServicedUntil());
        return servicingOperationsService.createAndAddActionToPart(serviceAction);
    }
}
