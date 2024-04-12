package org.example.client;

import org.example.storage.hibernate.HibernateUtil;
import org.example.ticket.TicketCrudService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudService {

    public HibernateUtil util;

    public ClientCrudService() {

        util = HibernateUtil.getINSTANCE();
    }

    public Client getClientById(long id) {
        Session session = util.getSessionFactory().openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }


    public List<Client> getAllClients() {
        Session session = util.getSessionFactory().openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        session.close();
        return clients;

    }

    public void createNewClient(String name) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client newClient = new Client();
        newClient.setName(name);
        session.persist(newClient);

        transaction.commit();

        session.close();
    }

    public void updateClient(int id, String name) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client existing = session.get(Client.class, id);
        existing.setName(name);
        session.persist(existing);

        transaction.commit();

        session.close();
    }

    public void deleteClientById(long id) {
        try (Session session = util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Client existing = session.get(Client.class, id);
            new TicketCrudService().deleteAllTicketsByClientId(id);
            session.remove(existing);
            transaction.commit();
        }

    }

    public boolean checkContainsClientId(long id) {

        List<Client> clients = getAllClients();
        for (Client client : clients) {
            if (client.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
