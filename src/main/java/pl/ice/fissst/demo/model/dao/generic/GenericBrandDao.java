package pl.ice.fissst.demo.model.dao.generic;

import org.hibernate.query.Query;
import pl.ice.fissst.demo.model.entity.Brand;

import java.util.List;

public abstract class GenericBrandDao extends GenericDao<Brand> {


    public Brand getBrandByID(long id) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Brand WHERE id = :id");
        query.setParameter("id", id);
        return (Brand)query.getSingleResult();
    }

    public List<Brand> getAllBrands(){
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Brand");
        return query.list();
    }
}
