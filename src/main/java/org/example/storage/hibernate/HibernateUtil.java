package org.example.storage.hibernate;

import lombok.Getter;
import org.example.client.Client;
import org.example.planet.Planet;
import org.example.ticket.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    @Getter
    private static final HibernateUtil INSTANCE;

    static {
        INSTANCE = new HibernateUtil();
    }

    @Getter
    private SessionFactory sessionFactory;

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();
    }

    public void close() {
        sessionFactory.close();
    }
}
