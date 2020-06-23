package pl.ice.fissst.demo.carparts.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ice.fissst.demo.brand.service.BrandListingService;
import pl.ice.fissst.demo.carparts.dao.PartDao;
import pl.ice.fissst.demo.carparts.dao.PartInfoDao;
import pl.ice.fissst.demo.model.entity.Model;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.model.entity.PartInfo;
import pl.ice.fissst.demo.model.util.External;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class PartsListingServiceImpl implements PartsListingService {

    @External
    private BrandListingService brandListingService;

    private PartInfoDao partInfoDao;

    private PartDao partDao;

    @Autowired
    public PartsListingServiceImpl(BrandListingService brandListingService, PartInfoDao partInfoDao, PartDao partDao) {
        this.brandListingService = brandListingService;
        this.partInfoDao = partInfoDao;
        this.partDao = partDao;
    }

    /**
     * Get list of part types from DB with matching Brand and Model names
     *
     * @param brand Brand name
     * @param model Model name
     * @return      List of part types of given brand and model.
     */
    @Override
    public List<PartInfo> getAllPartsForBrandAndModel(String brand, String model) {
        Model m = brandListingService.getModelByBrandAndModelName(brand,model);
        return partInfoDao.getAllPartsForModel(m);
    }

    /**
     * Get all parts types and coresponding parts for given model, filtered by part name and description.
     *
     * @param model             Model entity we fetch parts from
     * @param partName          Part name filter.
     * @param partDescription   Part description filter.
     * @return
     */
    @Override
    public List<PartInfo> getAllPartsForBrandAndModelFilteredByNameAndDescription(String brand, String model, String partName, String partDescription) {
        Model m = brandListingService.getModelByBrandAndModelName(brand,model);
        return partInfoDao.getAllPartsForModelWithFilter(m, partName, partDescription);
    }

    /**
     * Get the part from database with given id
     * @param id    Id of a part
     * @return      Part entity
     */
    @Override
    public Part getPartForId(long id) {
        Part part = null;
        try {
            part = partDao.getPartByID(id);
        } catch (NoResultException e){
            throw new IllegalArgumentException("No part was found for ID: " + id);
        } catch (PersistenceException e){
            throw new IllegalArgumentException("Database problem: " + e.getMessage());
        }

        return part;
    }
}
