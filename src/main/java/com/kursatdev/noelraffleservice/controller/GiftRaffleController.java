package com.kursatdev.noelraffleservice.controller;

import com.kursatdev.noelraffleservice.dto.GiftRaffleData;
import com.kursatdev.noelraffleservice.dto.NoelRaffleData;
import com.kursatdev.noelraffleservice.service.GiftRaffleService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
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
        giftRaffleService.executeGiftRaffle(giftRaffleData);
        return ResponseEntity.ok().build();
    }
}
