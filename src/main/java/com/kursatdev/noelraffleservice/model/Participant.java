package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Locale;

@Entity
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private Locale locale;

    @ManyToOne
    @JoinColumn(name = "noel_raffle_id")
    @ToString.Exclude
    private NoelRaffle noelRaffle;

    @ManyToOne
    @JoinColumn(name = "gift_raffle_id")
    @ToString.Exclude
    private GiftRaffle giftRaffle;
    public String getDisplayName() {
        return name + " " + surname;
    }
}
