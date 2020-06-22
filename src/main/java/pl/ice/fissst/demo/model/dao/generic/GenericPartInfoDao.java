package pl.ice.fissst.demo.model.dao.generic;

import org.hibernate.query.Query;
import pl.ice.fissst.demo.model.entity.PartInfo;

import java.util.List;

public abstract class GenericPartInfoDao extends GenericDao<PartInfo> {

    public PartInfo getPartInfoByID(long id) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.PartInfo WHERE id = :id");
        query.setParameter("id", id);
        return (PartInfo)query.getSingleResult();
    }

    public List<PartInfo> getAllPartInfos(){
        Query query = getCurrentSession().createQuery("FROM pl.ice.fissst.demo.model.entity.PartInfo");
        return query.list();
    }
}
