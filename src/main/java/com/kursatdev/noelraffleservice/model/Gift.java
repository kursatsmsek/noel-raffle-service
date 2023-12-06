package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int count;

    @ManyToOne
    @JoinColumn(name = "gift_raffle_id")
    private GiftRaffle giftRaffle;

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
