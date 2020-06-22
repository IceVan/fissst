package pl.ice.fissst.demo.stock.service;

import pl.ice.fissst.demo.model.entity.Part;

public interface StockListingService {

    Part checkPartInStock(long id);
}
