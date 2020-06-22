package pl.ice.fissst.demo.servicing.service;

import pl.ice.fissst.demo.model.entity.ServiceAction;
import pl.ice.fissst.demo.servicing.dto.AddServiceActionDTO;

public interface ServicingOperationsService {

    ServiceAction createAndAddActionToPart(AddServiceActionDTO serviceActionDTO);
}
