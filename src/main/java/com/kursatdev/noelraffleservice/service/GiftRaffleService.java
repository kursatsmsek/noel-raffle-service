package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.dto.GiftRaffleData;
import com.kursatdev.noelraffleservice.model.Gift;
import com.kursatdev.noelraffleservice.model.GiftRaffle;
import com.kursatdev.noelraffleservice.model.Participant;
import com.kursatdev.noelraffleservice.repository.GiftRaffleRepository;
import com.kursatdev.noelraffleservice.repository.GiftRepository;
import com.kursatdev.noelraffleservice.repository.ParticipantRepository;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class GiftRaffleService {

    private final GiftRaffleRepository giftRaffleRepository;

    private final MailService mailService;

    private final ParticipantService participantService;

    private final GiftService giftService;

    private final RaffleService raffleService;

    private final ParticipantRepository participantRepository;

    private final GiftRepository giftRepository;

    private final DirectExchange directExchange;

    private final RabbitTemplate rabbitTemplate;

    public GiftRaffleService(GiftRaffleRepository giftRaffleRepository, MailService mailService, ParticipantService participantService, GiftService giftService, RaffleService raffleService, ParticipantRepository participantRepository, GiftRepository giftRepository, DirectExchange directExchange, RabbitTemplate rabbitTemplate) {
        this.giftRaffleRepository = giftRaffleRepository;
        this.mailService = mailService;
        this.participantService = participantService;
        this.giftService = giftService;
        this.raffleService = raffleService;
        this.participantRepository = participantRepository;
        this.giftRepository = giftRepository;
        this.directExchange = directExchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${sr.rabbit.routing2.name}")
    private String routingName;

    public void executeGiftRaffle(GiftRaffleData giftRaffleData, Locale locale) {
        GiftRaffle giftRaffle = convertDataToGiftRaffle(giftRaffleData);
        giftRaffleRepository.save(giftRaffle);

        List<Participant> participants = giftRaffleData.getParticipants();
        for (Participant participant : participants) {
            participant.setGiftRaffle(giftRaffle);
            participant.setLocale(locale);
            participantRepository.save(participant);
        }

        List<Gift> gifts = giftRaffleData.getGifts();
        for (Gift gift: gifts) {
            gift.setGiftRaffle(giftRaffle);
            giftRepository.save(gift);
        }

        Map<Long, Long> matches = raffleService.performGiftRaffle(participants, gifts);
        rabbitTemplate.convertAndSend(directExchange.getName(), routingName, matches);
    }

    @RabbitListener(queues = "${sr.rabbit.queue2.name}")
    public void sendEmails(Map<Long, Long> matches) {
        matches.forEach((personId, giftId) -> {
            Participant person = participantService.getParticipantsById(personId);
            Gift gift = giftService.getGiftById(giftId);

            mailService.sendGiftEmail(person.getEmail(), person.getDisplayName(), gift.getName(), person.getGiftRaffle().getTitle(), person.getLocale());
        });
    }

    public GiftRaffle convertDataToGiftRaffle(GiftRaffleData giftRaffleData) {
        GiftRaffle giftRaffle = new GiftRaffle();
        giftRaffle.setTitle(giftRaffleData.getTitle());
        giftRaffle.setRaffleGroup(giftRaffleData.getGroup());
        giftRaffle.setSector(giftRaffleData.getSector());
        giftRaffle.setRaffleDate(new Date());

        return giftRaffle;
    }
}
