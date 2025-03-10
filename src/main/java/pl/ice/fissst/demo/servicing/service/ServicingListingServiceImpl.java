package pl.ice.fissst.demo.servicing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ice.fissst.demo.model.entity.ServiceAction;
import pl.ice.fissst.demo.servicing.dao.ServiceActionDao;

import java.sql.Date;
import java.util.List;

@Service
public class ServicingListingServiceImpl implements ServicingListingService {

    private ServiceActionDao serviceActionDao;

    @Autowired
    public ServicingListingServiceImpl(ServiceActionDao serviceActionDao) {
        this.serviceActionDao = serviceActionDao;
    }

    /**
     * Get all service actions for given period.
     * @param dateFrom     Starting date
     * @param dateUntil     Ending date
     * @return  List of service actions
     */
    @Override
    public List<ServiceAction> getServiceActionInGivenPeriod(Date dateFrom, Date dateUntil) {
        if(dateFrom.after(dateUntil)) throw new IllegalArgumentException("Starting date should not be after end date.");
        return serviceActionDao.getServiceActionsInPeriod(dateFrom,dateUntil);
    }

    /**
     * Get all service actions for given period and part.
     * @param id        Part Id
     * @param dateFrom     Starting date
     * @param dateUntil     Ending date
     * @return  List of service actions
     */
    @Override
    public List<ServiceAction> getServiceActionForPartInGivenPeriod(long id, Date dateFrom, Date dateUntil) {
        if(dateFrom.after(dateUntil)) throw new IllegalArgumentException("Starting date should not be after end date.");
        return serviceActionDao.getServiceActionsInPeriodForPart(id, dateFrom, dateUntil);
    }
}
