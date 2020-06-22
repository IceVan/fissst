package pl.ice.fissst.demo.servicing.dao;

import pl.ice.fissst.demo.model.dao.generic.GenericServiceActionDao;
import pl.ice.fissst.demo.model.entity.ServiceAction;

import java.sql.Date;
import java.util.List;

public abstract class ServiceActionDao extends GenericServiceActionDao {

    public abstract List<ServiceAction> getServiceActionsInPeriod(Date dateFrom, Date dateUntil);

    public abstract List<ServiceAction> getServiceActionsInPeriodForPart(long partId, Date dateFrom, Date dateUntil);
}
