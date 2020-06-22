package pl.ice.fissst.demo.carparts.dao;

import pl.ice.fissst.demo.model.dao.generic.GenericPartInfoDao;
import pl.ice.fissst.demo.model.entity.Model;
import pl.ice.fissst.demo.model.entity.PartInfo;

import java.util.List;

public abstract class PartInfoDao extends GenericPartInfoDao {

    public abstract List<PartInfo> getAllPartsForModel(Model model);

    public abstract List<PartInfo> getAllPartsForModelWithFilter(Model model, String partName, String partDescription);
}
