package org.example.planet;

import org.example.client.Client;
import org.example.storage.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {

    public HibernateUtil util;

    public PlanetCrudService() {

        util = HibernateUtil.getINSTANCE();
    }

    public Planet getPlanetById(int id) {
        Session session = util.getSessionFactory().openSession();
        Planet planet = session.get(Planet.class, id);
        session.close();
        return planet;
    }


    public List<Planet> getAllPlanets() {
        Session session = util.getSessionFactory().openSession();
        List<Planet> planets = session.createQuery("from Planet ", Planet.class).list();
        session.close();
        return planets;

    }

    public void createNewPlanet(String id, String name) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Planet newPlanet = new Planet();
        newPlanet.setId(id);
        newPlanet.setName(name);
        session.persist(newPlanet);

        transaction.commit();

        session.close();
    }

    public void updatePlanet(String id, String name) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Planet existing = session.get(Planet.class, id);
        existing.setName(name);
        session.persist(existing);

        transaction.commit();

        session.close();
    }

    public void deletePlanetById(String id) {
        try (Session session = util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Planet existing = session.get(Planet.class, id);
            session.remove(existing);
            transaction.commit();
        }

    }
    public boolean checkContainsPlanetId(String fromPlanet, String toPlanet) {

        boolean from = false;
        boolean to = false;
        List<Planet> planets = getAllPlanets();
        for (Planet planet : planets) {
            if (planet.getId().equals(fromPlanet)) {
                from = true;
            }
            if (planet.getId().equals(toPlanet)) {
                to = true;
            }
        }
        return from && to;
    }
}
