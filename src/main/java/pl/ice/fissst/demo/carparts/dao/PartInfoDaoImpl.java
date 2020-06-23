package pl.ice.fissst.demo.carparts.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import pl.ice.fissst.demo.model.entity.Model;
import pl.ice.fissst.demo.model.entity.PartInfo;

import java.util.List;

@Component
public class PartInfoDaoImpl extends PartInfoDao {

    /**
     * Get all parts types and coresponding parts for given model
     *
     * @param model     Model entity we fetch parts from
     * @return          List of parts types with corresponding parts
     */
    @Override
    public List<PartInfo> getAllPartsForModel(Model model) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.PartInfo pi INNER JOIN FETCH pi.parts WHERE pi.model = :model ");
        query.setParameter("model", model);
        return query.list();
    }

    /**
     * Get all parts types and coresponding parts for given model, filtered by part name and description.
     *
     * @param model             Model entity we fetch parts from
     * @param partName          Part name filter.
     * @param partDescription   Part description filter.
     * @return                  List of parts types with corresponding parts filtered by name or description
     */

    @Override
    public List<PartInfo> getAllPartsForModelWithFilter(Model model, String partName, String partDescription) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.PartInfo pi INNER JOIN FETCH pi.parts " +
                "WHERE pi.model = :model " +
                "AND lower(pi.name) LIKE lower(:partName) " +
                "AND lower(pi.description) LIKE lower(:partDescription)");
        query.setParameter("model", model)
                .setParameter("partName", "%" + (partName != null ? partName : "") + "%")
                .setParameter("partDescription", "%" + (partDescription != null ? partDescription : "") + "%");
        return query.list();
    }
}
