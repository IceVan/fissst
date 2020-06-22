package pl.ice.fissst.demo.model.dao.generic;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected Session getCurrentSession(){
        return entityManager.unwrap(Session.class);
    }

    public void setEntityManagerFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
