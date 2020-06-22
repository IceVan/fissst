package pl.ice.fissst.demo.servicing.service;

import pl.ice.fissst.demo.model.entity.ServiceAction;

import java.sql.Date;
import java.util.List;

public interface ServicingListingService {

    List<ServiceAction> getServiceActionInGivenPeriod(Date dateFrom, Date dateTo);

    List<ServiceAction> getServiceActionForPartInGivenPeriod(long id, Date dateFrom, Date dateTo);
}
