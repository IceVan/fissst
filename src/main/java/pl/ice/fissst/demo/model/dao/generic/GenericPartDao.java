package pl.ice.fissst.demo.model.dao.generic;

import org.hibernate.query.Query;
import pl.ice.fissst.demo.model.entity.Part;

import java.util.List;

public abstract class GenericPartDao extends GenericDao<Part> {

    public Part getPartByID(long id) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Part WHERE id = :id");
        query.setParameter("id", id);
        return (Part)query.getSingleResult();
    }

    public List<Part> getAllParts(){
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Part");
        return query.list();
    }
}
