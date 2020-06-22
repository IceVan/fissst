package pl.ice.fissst.demo.model.dao.generic;

import org.hibernate.query.Query;
import pl.ice.fissst.demo.model.entity.ServiceAction;

import java.util.List;

public abstract class GenericServiceActionDao extends GenericDao<ServiceAction>{

    public ServiceAction getServiceActionByID(long id) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.ServiceAction WHERE id = :id");
        query.setParameter("id", id);
        return (ServiceAction)query.getSingleResult();
    }

    public List<ServiceAction> getAllServiceActions(){
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.ServiceAction");
        return query.list();
    }
}
