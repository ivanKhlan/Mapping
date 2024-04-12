package org.example.planet;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.example.ticket.Ticket;

import java.util.HashSet;
import java.util.Set;

@Table(name = "planet")
@Entity
@Data
public class Planet {
    @Id
    @Pattern(regexp = "^[A-Z0-9]+$", message = "ID must consist of uppercase letters and digits only")
    private String id;

    @Column
    private String name;
}
