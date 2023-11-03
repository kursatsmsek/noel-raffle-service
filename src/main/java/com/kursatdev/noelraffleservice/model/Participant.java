package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getDisplayName() {
        return name + " " + surname;
    }
}
