package com.kursatdev.noelraffleservice.controller;

import com.kursatdev.noelraffleservice.dto.GiftRaffleData;
import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.model.Gift;
import com.kursatdev.noelraffleservice.service.GiftRaffleService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("gift")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class GiftRaffleController {

    private final MessageSource messageSource;

    private final GiftRaffleService giftRaffleService;

    @PostMapping("/raffle")
    public ResponseEntity<?> noelRaffle(@RequestBody GiftRaffleData giftRaffleData,
                                        @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        int totalGiftCount = giftRaffleData.getGifts().stream().mapToInt(Gift::getCount).sum();

        if (giftRaffleData.getParticipants().size() > 500) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageSource.getMessage("participants-limit", null, locale));
        } else if (giftRaffleData.getParticipants().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageSource.getMessage("participants-cannot-be-empty", null, locale));
        } else if (giftRaffleData.getTitle() == null || giftRaffleData.getTitle().isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageSource.getMessage("title-cannot-be-null", null, locale));
        } else if (totalGiftCount > giftRaffleData.getParticipants().size()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageSource.getMessage("gift-participant-limit", null, locale));
        }

        try {
            giftRaffleService.executeGiftRaffle(giftRaffleData);
            return ResponseEntity.ok().body(messageSource.getMessage("noel-raffle-success", null, locale));

        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(messageSource.getMessage("something-went-wrong", null, locale));
        }
    }
}
