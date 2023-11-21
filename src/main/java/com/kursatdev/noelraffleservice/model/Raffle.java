package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@MappedSuperclass
@Data
public abstract class Raffle {

    private String title;

    private String raffleGroup;

    private String sector;

    private Date raffleDate;

    public abstract Map<Participant, ?> performRaffle();

}
