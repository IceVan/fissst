package pl.ice.fissst.demo.brand.dao;

import pl.ice.fissst.demo.model.dao.generic.GenericBrandDao;
import pl.ice.fissst.demo.model.entity.Brand;

public abstract class BrandDao extends GenericBrandDao {

    public abstract Brand getBrandByName(String name);
}
