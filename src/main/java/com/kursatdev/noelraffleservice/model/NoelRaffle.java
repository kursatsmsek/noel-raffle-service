package com.kursatdev.noelraffleservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class NoelRaffle extends Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "noelRaffle", cascade = CascadeType.ALL)
    private List<Participant> participants;

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
