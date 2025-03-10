package pl.ice.fissst.demo.servicing.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import pl.ice.fissst.demo.model.entity.ServiceAction;

import java.sql.Date;
import java.util.List;

@Component
public class ServiceActionDaoImpl extends ServiceActionDao {

    /**
     * Get all service actions for given period.
     * @param dateFrom     Starting date
     * @param dateUntil     Ending date
     * @return  List of service actions
     */
    @Override
    public List<ServiceAction> getServiceActionsInPeriod(Date dateFrom, Date dateUntil) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.ServiceAction sa WHERE sa.servicedSince between :dateFrom AND :dateUntil");
        query.setParameter("dateFrom", dateFrom).setParameter("dateUntil", dateUntil);
        return query.list();
    }

    /**
     * Get all service actions for given period and part.
     * @param partId        Part Id
     * @param dateFrom     Starting date
     * @param dateUntil     Ending date
     * @return  List of service actions
     */
    @Override
    public List<ServiceAction> getServiceActionsInPeriodForPart(long partId, Date dateFrom, Date dateUntil) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.ServiceAction sa WHERE sa.part.id = :partId and sa.servicedSince between :dateFrom AND :dateUntil");
        query.setParameter("dateFrom", dateFrom).setParameter("dateUntil", dateUntil).setParameter("partId", partId);
        return query.list();
    }
}
