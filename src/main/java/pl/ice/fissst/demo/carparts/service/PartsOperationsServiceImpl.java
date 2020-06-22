package pl.ice.fissst.demo.carparts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ice.fissst.demo.carparts.dao.PartDao;
import pl.ice.fissst.demo.model.entity.PartInfo;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Service
public class PartsOperationsServiceImpl implements PartsOperationsService {

    private PartDao partDao;

    @Autowired
    public PartsOperationsServiceImpl(PartDao partDao) {
        this.partDao = partDao;
    }

    @Transactional
    @Override
    public PartInfo changePartDescriptionWithId(long id, String description) {
        PartInfo partInfo = null;
        try{
            partInfo = partDao.getPartByID(id).getPartInfo();
            partInfo.setDescription(description);
        } catch (NoResultException e){
            throw new IllegalArgumentException("No part was found for ID: " + id);
        } catch (PersistenceException e){
            throw new IllegalArgumentException("Database problem: " + e.getMessage());
        }

        return partInfo;
    }
}
