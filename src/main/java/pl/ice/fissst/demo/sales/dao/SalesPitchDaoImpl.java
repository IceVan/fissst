package pl.ice.fissst.demo.sales.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import pl.ice.fissst.demo.model.entity.Part;

@Component
public class SalesPitchDaoImpl extends SalesPitchDao {

    /**
     * Remove all sales pitches from part with given ID
     * @param part  Part id
     */
    @Override
    public void removeSalesPitchesFromPart(Part part) {
        Query query = getCurrentSession().createQuery("DELETE FROM pl.ice.fissst.demo.model.entity.SalesPitch sp WHERE sp.part = :part");
        query.setParameter("part", part);
        query.executeUpdate();
    }
}
