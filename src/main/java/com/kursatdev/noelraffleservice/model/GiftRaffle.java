package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class GiftRaffle extends Raffle {

    @Id
    private Long id;

    @OneToMany(mappedBy = "giftRaffle")
    private List<Participant> participants;

    @Override
    public void performRaffle() {
        System.out.println("performing gift raffle");
    }
}
