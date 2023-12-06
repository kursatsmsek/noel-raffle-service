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
    public Map<Participant, Gift> performRaffle() {
        Map<Participant, Gift> result = new HashMap<>();
        List<Gift> availableGifts = new ArrayList<>();

        gifts.forEach(gift -> IntStream.range(0, gift.getCount()).forEach(i -> availableGifts.add(gift)));

        List<Participant> shuffledParticipants = new ArrayList<>(participants);
        Collections.shuffle(shuffledParticipants);

        IntStream.range(0, Math.min(shuffledParticipants.size(), availableGifts.size()))
                .forEach(i -> result.put(shuffledParticipants.get(i), availableGifts.get(i)));

        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
