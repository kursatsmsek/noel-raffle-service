package com.kursatdev.noelraffleservice.controller;

import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.model.Participant;
import com.kursatdev.noelraffleservice.service.MailService;
import com.kursatdev.noelraffleservice.service.NoelRaffleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

@RestController
@RequestMapping(path = "noel")
@AllArgsConstructor
public class NoelRaffleController {

    private final NoelRaffleService noelRaffleService;

    private final MailService mailService;

    @PostMapping ("/raffle")
    public ResponseEntity<?> noelRaffle(@RequestBody NoelRaffleData noelRaffleData) {
        noelRaffleService.executeRaffle(noelRaffleData);
        return ResponseEntity.ok("ok");
    }

}
