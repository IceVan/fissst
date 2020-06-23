package pl.ice.fissst.demo.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ice.fissst.demo.carparts.service.PartsListingService;
import pl.ice.fissst.demo.model.entity.Part;
import pl.ice.fissst.demo.model.util.External;

@Service
public class StockListingServiceImpl implements StockListingService {

    @External
    private PartsListingService partsListingService;

    @Autowired
    public StockListingServiceImpl(PartsListingService partsListingService) {
        this.partsListingService = partsListingService;
    }

    /**
     * Gets part stock information for part with given id
     * @param id    Part id
     * @return      Part entity
     */
    @Override
    public Part checkPartInStock(long id) {
        return partsListingService.getPartForId(id);
    }
}
