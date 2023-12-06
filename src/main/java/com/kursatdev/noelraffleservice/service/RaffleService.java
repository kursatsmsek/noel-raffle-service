package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.model.Gift;
import com.kursatdev.noelraffleservice.model.Participant;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class RaffleService {

    public Map<Long, Long> performNoelRaffle(List<Participant> participants) {
        Collections.shuffle(participants);
        Map<Long, Long> matches = new HashMap<>();

        for (int i = 0; i < participants.size(); i++) {
            Long giver = participants.get(i).getId();
            Long receiver = participants.get((i + 1) % participants.size()).getId();

            matches.put(giver, receiver);
        }

        return matches;
    }

    public Map<Long, Long> performGiftRaffle(List<Participant> participants, List<Gift> gifts) {
        Map<Long, Long> result = new HashMap<>();
        List<Gift> availableGifts = new ArrayList<>();

        gifts.forEach(gift -> IntStream.range(0, gift.getCount()).forEach(i -> availableGifts.add(gift)));

        List<Participant> shuffledParticipants = new ArrayList<>(participants);
        Collections.shuffle(shuffledParticipants);

        IntStream.range(0, Math.min(shuffledParticipants.size(), availableGifts.size()))
                .forEach(i -> result.put(shuffledParticipants.get(i).getId(), availableGifts.get(i).getId()));

        return result;
    }
}
