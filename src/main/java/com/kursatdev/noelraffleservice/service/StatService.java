package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.model.Stat;
import com.kursatdev.noelraffleservice.repository.GiftRaffleRepository;
import com.kursatdev.noelraffleservice.repository.GiftRepository;
import com.kursatdev.noelraffleservice.repository.NoelRaffleRepository;
import com.kursatdev.noelraffleservice.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatService {
    private final NoelRaffleRepository noelRaffleRepository;

    private final GiftRaffleRepository giftRaffleRepository;

    private final ParticipantRepository participantRepository;

    private final GiftRepository giftRepository;

    public Stat getStats() {
        return new Stat(
                (int) noelRaffleRepository.count(),
                (int) giftRaffleRepository.count(),
                (int) participantRepository.count(),
                (int) giftRepository.count());
    }
}
