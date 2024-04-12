package org.example;

import org.example.client.ClientCrudService;
import org.example.planet.PlanetCrudService;
import org.example.storage.DatabaseInitService;
import org.example.ticket.TicketCrudService;

public class Main {
    public static void main(String[] args) {

        new DatabaseInitService().initDb();
//
//        new ClientCrudService().createNewClient("some name");
//        new ClientCrudService().updateClient(10, "change name");
//        new ClientCrudService().deleteClientById(10);
//        System.out.println("new ClientCrudService().getAllClients() = " +
//        new ClientCrudService().getAllClients());
//
        System.out.println("new TicketCrudService().getTicketByClientId(1) = " +
                new TicketCrudService().getTicketByClientId(1));
//
//        new TicketCrudService().createNewTicket(10, "NEPTUNE01", "MERCURY05");
//
//        new TicketCrudService().updateTicket(10, 10, "URANUS02", "URANUS02");
//        new TicketCrudService().deleteTicketByClientId(10);
//        System.out.println(new TicketCrudService().getAllTickets());
//
//        new PlanetCrudService().createNewPlanet("TEST00", "test planet");
//        new PlanetCrudService().updatePlanet("TEST00", "some name");
//        new PlanetCrudService().deletePlanetById("TEST00");
//        System.out.println("new PlanetCrudService().getAllPlanets() = " +
//        new PlanetCrudService().getAllPlanets());




    }

}