package org.example.ticket;


import jakarta.persistence.*;
import lombok.Data;
import org.example.client.Client;
import org.example.planet.Planet;

@Table(name = "ticket")
@Entity
@Data
public class Ticket {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private String created_at;

    @Column(name = "client_id", insertable = false, updatable = false)
    private long client_id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet from_planet_id;

    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet to_planet_id;
}
