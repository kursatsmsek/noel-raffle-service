package com.kursatdev.noelraffleservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Stat {
    private int noelRaffleCount;
    private int giftRaffleCount;
    private int participantCount;
    private int giftCount;
    private int totalRaffleCount = noelRaffleCount + giftRaffleCount;

    public Stat(int noelRaffleCount, int giftRaffleCount, int participantCount, int giftCount) {
        this.noelRaffleCount = noelRaffleCount;
        this.giftRaffleCount = giftRaffleCount;
        this.participantCount = participantCount;
        this.giftCount = giftCount;
        this.totalRaffleCount = noelRaffleCount + giftRaffleCount;
    }
}
