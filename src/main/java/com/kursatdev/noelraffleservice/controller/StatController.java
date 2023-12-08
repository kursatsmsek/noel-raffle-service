package com.kursatdev.noelraffleservice.controller;

import com.kursatdev.noelraffleservice.model.Stat;
import com.kursatdev.noelraffleservice.service.StatService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("stats")
@AllArgsConstructor
public class StatController {
    private final MessageSource messageSource;

    private final StatService statService;

    @PostMapping("/")
    public ResponseEntity<?> getStats(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            Stat stats = statService.getStats();
            return ResponseEntity.ok().body(stats);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(messageSource.getMessage("something-went-wrong", null, locale));
        }
    }
}
