package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.model.NoelRaffle;
import com.kursatdev.noelraffleservice.model.Participant;
import com.kursatdev.noelraffleservice.repository.NoelRaffleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class NoelRaffleService {

    private final NoelRaffleRepository noelRaffleRepository;

    private final ParticipantService participantService;

    public void executeRaffle(NoelRaffleData noelRaffleData) {
        NoelRaffle noelRaffle = new NoelRaffle();
        noelRaffle.setTitle(noelRaffleData.getTitle());
        noelRaffle.setRaffleGroup(noelRaffleData.getGroup());
        noelRaffle.setSector(noelRaffleData.getSector());
        noelRaffle.setRaffleDate(new Date());

        List<Participant> participants = noelRaffleData.getParticipants();
        for (Participant participant : participants) {
            participant.setNoelRaffle(noelRaffle);
        }
        noelRaffle.setParticipants(participants);

        noelRaffleRepository.save(noelRaffle);
        participantService.saveParticipantList(participants);
        noelRaffle.performRaffle();
    }
}
