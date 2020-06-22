package pl.ice.fissst.demo.brand.dao;

import pl.ice.fissst.demo.model.dao.generic.GenericModelDao;
import pl.ice.fissst.demo.model.entity.Model;

public abstract class ModelDao extends GenericModelDao {

    public abstract Model getModelByBrandAndModelName(String brand, String model);
}
