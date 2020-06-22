package pl.ice.fissst.demo.model.dao.generic;

import org.hibernate.query.Query;
import pl.ice.fissst.demo.model.entity.SalesPitch;

import java.util.List;

public abstract class GenericSalesPitchDao extends GenericDao<SalesPitch> {

    public SalesPitch getSalesPitchByID(long id) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.SalesPitch WHERE id = :id");
        query.setParameter("id", id);
        return (SalesPitch)query.getSingleResult();
    }

    public List<SalesPitch> getAllSalesPitches(){
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.SalesPitch");
        return query.list();
    }
}
