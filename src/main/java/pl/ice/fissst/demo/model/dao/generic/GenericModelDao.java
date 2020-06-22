package pl.ice.fissst.demo.model.dao.generic;

import org.hibernate.query.Query;
import pl.ice.fissst.demo.model.entity.Model;

import java.util.List;

public abstract class GenericModelDao extends GenericDao<Model> {

    public Model getModelByID(long id) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Model WHERE id = :id");
        query.setParameter("id", id);
        return (Model)query.getSingleResult();
    }

    public List<Model> getAllModels(){
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Model");
        return query.list();
    }
}
