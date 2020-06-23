package pl.ice.fissst.demo.brand.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import pl.ice.fissst.demo.model.entity.Brand;
import pl.ice.fissst.demo.model.entity.Model;

@Component
public class BrandDaoImpl extends BrandDao {

    /**
     * Get Brand entity from DB based on brand name
     * @param name  Brand name
     * @return      Brand entity with given name
     */
    @Override
    public Brand getBrandByName(String name) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Brand b " +
                "inner join fetch b.models m " +
                "WHERE lower(b.name) = lower(:name) ");
        query.setParameter("name", name);
        return (Brand)query.getSingleResult();
    }
}
