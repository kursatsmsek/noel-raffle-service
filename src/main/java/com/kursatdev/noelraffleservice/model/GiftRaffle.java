package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
public class GiftRaffle extends Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "giftRaffle", cascade = CascadeType.ALL)
    private List<Participant> participants;

    @Override
    public Map<Participant, Participant> performRaffle() {
        System.out.println("performing gift raffle");
        return null;
    }
}
