package com.kursatdev.noelraffleservice.controller;

import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.service.NoelRaffleService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Objects;

@RestController
@RequestMapping(path = "noel")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class NoelRaffleController {
    private final MessageSource messageSource;

    private final NoelRaffleService noelRaffleService;

    @PostMapping ("/raffle")
    public ResponseEntity<?> noelRaffle(@RequestBody NoelRaffleData noelRaffleData,
                                        @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        if (noelRaffleData.getParticipants().size() > 500) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageSource.getMessage("participants-limit", null, locale));
        } else if (noelRaffleData.getParticipants().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageSource.getMessage("participants-cannot-be-empty", null, locale));
        } else if (noelRaffleData.getTitle() == null || noelRaffleData.getTitle().isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageSource.getMessage("title-cannot-be-null", null, locale));
        }

        try {
            locale = Objects.requireNonNullElse(locale, Locale.ENGLISH);
            noelRaffleService.executeRaffle(noelRaffleData, locale);
            return ResponseEntity.ok().body(messageSource.getMessage("noel-raffle-success", null, locale));

        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(messageSource.getMessage("something-went-wrong", null, locale));
        }
    }

}
