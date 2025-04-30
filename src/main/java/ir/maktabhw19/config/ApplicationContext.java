package ir.maktabhw19.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Objects;

public class ApplicationContext {
    private static ApplicationContext applicationContext;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance(){
        if(applicationContext == null){
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        if(Objects.isNull(entityManagerFactory)){
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
        }
        return entityManagerFactory;
    }

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        if(Objects.isNull(entityManager)){
            entityManager = getEntityManagerFactory().createEntityManager();
        }
        return entityManager;
    }
}
