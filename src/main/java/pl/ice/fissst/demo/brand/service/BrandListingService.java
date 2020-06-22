package pl.ice.fissst.demo.brand.service;

import pl.ice.fissst.demo.model.entity.Brand;
import pl.ice.fissst.demo.model.entity.Model;

public interface BrandListingService {

    Brand getBrandInfo(String name);

    Model getModelByBrandAndModelName(String brand, String model);
}
