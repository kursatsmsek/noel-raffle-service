package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.dto.GiftRaffleData;
import com.kursatdev.noelraffleservice.model.Gift;
import com.kursatdev.noelraffleservice.model.GiftRaffle;
import com.kursatdev.noelraffleservice.model.NoelRaffle;
import com.kursatdev.noelraffleservice.model.Participant;
import com.kursatdev.noelraffleservice.repository.GiftRaffleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class GiftRaffleService {

    private final GiftRaffleRepository giftRaffleRepository;

    private final MailService mailService;

    public void executeGiftRaffle(GiftRaffleData giftRaffleData) {
        GiftRaffle giftRaffle = convertDataToGiftRaffle(giftRaffleData);

        giftRaffleRepository.save(giftRaffle);
//        Map<Participant, Participant> matches = giftRaffle.performRaffle();
//        matches.forEach((Participant giver, Participant receiver) -> {
//            mailService.sendEmail(giver.getEmail(), giver.getDisplayName(), receiver.getDisplayName(), giftRaffle.getTitle());
//        });
    }

    public GiftRaffle convertDataToGiftRaffle(GiftRaffleData giftRaffleData) {
        GiftRaffle giftRaffle = new GiftRaffle();
        giftRaffle.setTitle(giftRaffleData.getTitle());
        giftRaffle.setRaffleGroup(giftRaffle.getRaffleGroup());
        giftRaffle.setSector(giftRaffleData.getSector());
        giftRaffle.setRaffleDate(new Date());

        List<Participant> participants = giftRaffleData.getParticipants();
        for (Participant participant : participants) {
            participant.setGiftRaffle(giftRaffle);
        }
        giftRaffle.setParticipants(participants);

        List<Gift> gifts = giftRaffleData.getGifts();
        for (Gift gift: gifts) {
            gift.setGiftRaffle(giftRaffle);
        }
        giftRaffle.setGifts(gifts);

        return giftRaffle;
    }
}
