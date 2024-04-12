package org.example.client;

import jakarta.persistence.*;
import lombok.Data;
import org.example.ticket.Ticket;

import java.util.HashSet;
import java.util.Set;

@Table(name = "client")
@Entity
@Data
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();
//
}
