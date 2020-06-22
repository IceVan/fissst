package pl.ice.fissst.demo.brand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ice.fissst.demo.brand.dao.BrandDao;
import pl.ice.fissst.demo.brand.dao.ModelDao;
import pl.ice.fissst.demo.model.entity.Brand;
import pl.ice.fissst.demo.model.entity.Model;

@Service
public class BrandListingServiceImpl implements BrandListingService {

    private ModelDao modelDao;

    private BrandDao brandDao;

    @Autowired
    public BrandListingServiceImpl(ModelDao modelDao, BrandDao brandDao) {
        this.modelDao = modelDao;
        this.brandDao = brandDao;
    }

    @Override
    public Brand getBrandInfo(String name) {
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Brand name should not be empty.");
        return brandDao.getBrandByName(name);
    }

    @Override
    public Model getModelByBrandAndModelName(String brand, String model) {
        if(brand == null || brand.isEmpty() || model == null || model.isEmpty()) throw new IllegalArgumentException("Brand and model name should not be empty.");
        return modelDao.getModelByBrandAndModelName(brand, model);
    }
}
