package pl.ice.fissst.demo.brand.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import pl.ice.fissst.demo.model.entity.Model;

@Component
public class ModelDaoImpl extends ModelDao {

    @Override
    public Model getModelByBrandAndModelName(String brand, String model) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.Model m WHERE m.name = :modelName AND m.brand.name = :brandName");
        query.setParameter("modelName", model)
                .setParameter("brandName", brand);
        return (Model)query.getSingleResult();
    }
}
