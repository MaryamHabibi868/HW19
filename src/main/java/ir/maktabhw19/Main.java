package ir.maktabhw19;


import ir.maktabhw19.config.ApplicationContext;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager = ApplicationContext.getInstance().getEntityManager();
    }
}