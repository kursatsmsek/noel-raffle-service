package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.model.Participant;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
