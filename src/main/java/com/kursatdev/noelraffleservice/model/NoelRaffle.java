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
    public Map<Participant, Participant> performRaffle() {
        Collections.shuffle(participants);
        Map<Participant, Participant> matches = new HashMap<>();

        for (int i = 0; i < participants.size(); i++) {
            Participant giver = participants.get(i);
            Participant receiver = participants.get((i + 1) % participants.size());

            matches.put(giver, receiver);
        }

        return matches;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
