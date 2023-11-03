package com.kursatdev.noelraffleservice.controller;

import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.model.Participant;
import com.kursatdev.noelraffleservice.service.NoelRaffleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "noel")
@AllArgsConstructor
public class NoelRaffleController {

    private final NoelRaffleService noelRaffleService;

    @PostMapping ("/raffle")
    public ResponseEntity<?> noelRaffle(@RequestBody NoelRaffleData noelRaffleData) {
        noelRaffleService.executeRaffle(noelRaffleData);
        return ResponseEntity.ok("ok");
    }
}
