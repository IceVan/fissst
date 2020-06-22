package pl.ice.fissst.demo.carparts.service;

import pl.ice.fissst.demo.model.entity.Brand;
import pl.ice.fissst.demo.model.entity.Model;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.model.entity.PartInfo;

import java.util.List;

public interface PartsListingService {

    List<PartInfo> getAllPartsForBrandAndModel(String brand, String model);

    List<PartInfo> getAllPartsForBrandAndModelFilteredByNameAndDescription(String brand, String model, String partName, String partDescription);

    Part getPartForId(long id);
}
