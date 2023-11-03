package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String surname;

    private String email;

    @ManyToOne
    @JoinColumn(name = "noel_raffle_id")
    private NoelRaffle noelRaffle;

    @ManyToOne
    @JoinColumn(name = "gift_raffle_id")
    private GiftRaffle giftRaffle;

}
