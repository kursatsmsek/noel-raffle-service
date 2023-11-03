package com.kursatdev.noelraffleservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class NoelRaffle extends Raffle{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "noelRaffle")
    private List<Participant> participants;

    @Override
    public void performRaffle() {
        System.out.println("performing noel raffle");
    }

}
