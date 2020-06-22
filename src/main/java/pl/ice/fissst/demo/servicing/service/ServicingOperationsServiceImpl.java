package pl.ice.fissst.demo.servicing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ice.fissst.demo.carparts.service.PartsListingService;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.model.entity.ServiceAction;
import pl.ice.fissst.demo.model.util.External;
import pl.ice.fissst.demo.servicing.dao.ServiceActionDao;
import pl.ice.fissst.demo.servicing.dto.AddServiceActionDTO;

import java.sql.Date;

@Service
public class ServicingOperationsServiceImpl implements ServicingOperationsService {

    @External
    private PartsListingService partsListingService;

    @Autowired
    public ServicingOperationsServiceImpl(PartsListingService partsListingService) {
        this.partsListingService = partsListingService;
    }

    @Transactional
    @Override
    public ServiceAction createAndAddActionToPart(AddServiceActionDTO serviceActionDTO) {
        if(serviceActionDTO.getServicedFrom().after(serviceActionDTO.getServicedUntil())) throw new IllegalArgumentException("Starting date should not be after end date.");

        ServiceAction serviceAction = new ServiceAction();
        serviceAction.setName(serviceActionDTO.getName());
        serviceAction.setServicedSince(serviceActionDTO.getServicedFrom());
        serviceAction.setServicedUntil(serviceActionDTO.getServicedUntil());

        Part part = partsListingService.getPartForId(serviceActionDTO.getPartId());
        part.addServiceAction(serviceAction);

        return serviceAction;
    }

}
