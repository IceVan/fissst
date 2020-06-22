package pl.ice.fissst.demo.model.dao.generic;

import org.hibernate.query.Query;
import pl.ice.fissst.demo.model.entity.Production;

import java.util.List;

public abstract class GenericProductionDao extends GenericDao<Production> {

    public Production getProductionByID(long id) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Production WHERE id = :id");
        query.setParameter("id", id);
        return (Production)query.getSingleResult();
    }

    public List<Production> getAllProductions(){
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Production");
        return query.list();
    }
}
