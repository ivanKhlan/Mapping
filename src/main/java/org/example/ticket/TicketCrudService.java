package org.example.ticket;

import jakarta.transaction.Transactional;
import org.example.storage.hibernate.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketCrudService {
    public HibernateUtil util;

    public TicketCrudService() {

        util = HibernateUtil.getINSTANCE();
    }

    public Ticket getTicketByClientId(int id) {
        Session session = util.getSessionFactory().openSession();
        Ticket ticket = session.get(Ticket.class, id);
        session.close();
        return ticket;
    }



    public List<Ticket> getAllTickets() {
        Session session = util.getSessionFactory().openSession();
        List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).list();
        session.close();
        return tickets;

    }

    public void createNewTicket(long clientId, String from_planet_id, String to_planet_id) {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Ticket newTicket = new Ticket();
        newTicket.setCreated_at(formattedDate);
        newTicket.setClient_id(clientId);
        newTicket.setFrom_planet_id(from_planet_id);
        newTicket.setTo_planet_id(to_planet_id);

        session.persist(newTicket);

        transaction.commit();

        session.close();
    }

    public void updateTicket(long id, long client_id, String from_planet_id, String to_planet_id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Ticket existing = session.get(Ticket.class, id);
        existing.setTo_planet_id(to_planet_id);
        session.persist(existing);

        transaction.commit();

        session.close();
    }

    public void deleteTicketByClientId(long id) {
        try (Session session = util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Ticket existing = session.get(Ticket.class, id);
            session.remove(existing);
            transaction.commit();
        }

    }

    public void deleteAllTicketsByClientId(long clientId) {

        try (Session session = util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            List<Ticket> tickets = getAllTickets();
            for (Ticket ticket : tickets) {
                if (clientId == ticket.getClient_id()) {
                    deleteTicketByClientId(ticket.getClient_id());
                }
            }
            transaction.commit();
        }
    }
}
