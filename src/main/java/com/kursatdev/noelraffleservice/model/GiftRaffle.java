package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;
import java.util.stream.IntStream;

@Entity
@Data
public class GiftRaffle extends Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "giftRaffle", cascade = CascadeType.ALL)
    private List<Participant> participants;

    @OneToMany(mappedBy = "giftRaffle", cascade = CascadeType.ALL)
    private List<Gift> gifts;

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
