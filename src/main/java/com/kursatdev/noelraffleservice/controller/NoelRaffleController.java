package com.kursatdev.noelraffleservice.controller;

import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.service.NoelRaffleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "noel")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class NoelRaffleController {

    private final NoelRaffleService noelRaffleService;

    @PostMapping ("/raffle")
    public ResponseEntity<?> noelRaffle(@RequestBody NoelRaffleData noelRaffleData) {
        try {
            if (noelRaffleData.getParticipants().size() > 500) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Participants count cannot exceed 500");
            } else if (!noelRaffleData.getTitle().isBlank()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Title cannot be empty");
            }

            noelRaffleService.executeRaffle(noelRaffleData);
            return ResponseEntity.ok().build();

        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

}
