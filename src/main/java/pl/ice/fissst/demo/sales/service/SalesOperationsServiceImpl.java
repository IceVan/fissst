package pl.ice.fissst.demo.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ice.fissst.demo.carparts.service.PartsListingService;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.model.util.External;
import pl.ice.fissst.demo.sales.dao.SalesPitchDao;


@Service
public class SalesOperationsServiceImpl implements SalesOperationsService {

    private SalesPitchDao salesPitchDao;

    @External
    private PartsListingService partsListingService;

    @Autowired
    public SalesOperationsServiceImpl(SalesPitchDao salesPitchDao, PartsListingService partsListingService) {
        this.salesPitchDao = salesPitchDao;
        this.partsListingService = partsListingService;
    }

    @Transactional
    @Override
    public boolean removeSalesPitchesFromPartWithId(long id) {
        Part part = partsListingService.getPartForId(id);

        salesPitchDao.removeSalesPitchesFromPart(part);

        return true;
    }
}
