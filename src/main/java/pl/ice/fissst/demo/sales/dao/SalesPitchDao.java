package pl.ice.fissst.demo.sales.dao;

import pl.ice.fissst.demo.model.dao.generic.GenericSalesPitchDao;
import pl.ice.fissst.demo.model.entity.Part;

public abstract class SalesPitchDao extends GenericSalesPitchDao {

    public abstract void removeSalesPitchesFromPart(Part part);
}
