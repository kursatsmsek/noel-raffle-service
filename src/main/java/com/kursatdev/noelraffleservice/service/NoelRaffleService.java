package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.model.NoelRaffle;
import com.kursatdev.noelraffleservice.model.Participant;
import com.kursatdev.noelraffleservice.repository.NoelRaffleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class NoelRaffleService {

    private final NoelRaffleRepository noelRaffleRepository;

    private final MailService mailService;

    public void executeRaffle(NoelRaffleData noelRaffleData) throws Exception {
        NoelRaffle noelRaffle = convertDataToNoelRaffle(noelRaffleData);

        noelRaffleRepository.save(noelRaffle);
        Map<Participant, Participant> matches = noelRaffle.performRaffle();
        matches.forEach((Participant giver, Participant receiver) -> {
            mailService.sendEmail(giver.getEmail(), giver.getDisplayName(), receiver.getDisplayName(), noelRaffle.getTitle());
        });
    }

    public NoelRaffle convertDataToNoelRaffle(NoelRaffleData noelRaffleData) {
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

        return noelRaffle;
    }
}
