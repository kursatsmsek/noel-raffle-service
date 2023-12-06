package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.model.Participant;
import com.kursatdev.noelraffleservice.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public void saveParticipantList(List<Participant> participants) {
        participantRepository.saveAll(participants);
    }

    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    public Participant getParticipantsById(Long id) {
        return participantRepository.findById(id).orElseThrow();
    }
}
