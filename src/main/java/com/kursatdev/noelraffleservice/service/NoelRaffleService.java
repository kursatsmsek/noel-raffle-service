package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.model.NoelRaffle;
import com.kursatdev.noelraffleservice.model.Participant;
import com.kursatdev.noelraffleservice.repository.NoelRaffleRepository;
import com.kursatdev.noelraffleservice.repository.ParticipantRepository;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoelRaffleService {

    private final NoelRaffleRepository noelRaffleRepository;

    private final ParticipantRepository participantRepository;

    private final MailService mailService;

    private final ParticipantService participantService;

    private final RaffleService raffleService;

    private final DirectExchange directExchange;

    private final RabbitTemplate rabbitTemplate;
    public NoelRaffleService(NoelRaffleRepository noelRaffleRepository, ParticipantRepository participantRepository, MailService mailService, ParticipantService participantService, RaffleService raffleService, DirectExchange directExchange, RabbitTemplate rabbitTemplate) {
        this.noelRaffleRepository = noelRaffleRepository;
        this.participantRepository = participantRepository;
        this.mailService = mailService;
        this.participantService = participantService;
        this.raffleService = raffleService;
        this.directExchange = directExchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${sr.rabbit.routing1.name}")
    private String routingName;

    public void executeRaffle(NoelRaffleData noelRaffleData, Locale locale) {
        NoelRaffle noelRaffle = convertDataToNoelRaffle(noelRaffleData);
        noelRaffleRepository.save(noelRaffle);

        List<Participant> participants = noelRaffleData.getParticipants();
        for (Participant participant : participants) {
            participant.setNoelRaffle(noelRaffle);
            participant.setLocale(locale);
            participantRepository.save(participant);
        }

        Map<Long, Long> matches = raffleService.performNoelRaffle(participants);

        rabbitTemplate.convertAndSend(directExchange.getName(), routingName, matches);
    }

    @RabbitListener(queues = "${sr.rabbit.queue1.name}")
    public void sendEmails(Map<Long, Long> matches) {
        matches.forEach((giverId, receiverId) -> {
            Participant giver = participantService.getParticipantsById(giverId);
            Participant receiver = participantService.getParticipantsById(receiverId);

            mailService.sendEmail(giver.getEmail(), giver.getDisplayName(), receiver.getDisplayName(), giver.getNoelRaffle().getTitle(), giver.getLocale());
        });
    }

    public NoelRaffle convertDataToNoelRaffle(NoelRaffleData noelRaffleData) {
        NoelRaffle noelRaffle = new NoelRaffle();
        noelRaffle.setTitle(noelRaffleData.getTitle());
        noelRaffle.setRaffleGroup(noelRaffleData.getGroup());
        noelRaffle.setSector(noelRaffleData.getSector());
        noelRaffle.setRaffleDate(new Date());

        return noelRaffle;
    }
}
