package com.kursatdev.noelraffleservice.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "noel")
public class NoelRaffleController {

    @GetMapping ("/raffle")
    public ResponseEntity<?> noelRaffle() {
        return ResponseEntity.ok("ok");
    }
}
