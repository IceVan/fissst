package pl.ice.fissst.demo.carparts.service;

import pl.ice.fissst.demo.model.entity.PartInfo;

public interface PartsOperationsService {

    PartInfo changePartDescriptionWithId(long id, String description);
}
