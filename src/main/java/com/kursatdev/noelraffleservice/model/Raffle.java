package com.kursatdev.noelraffleservice.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public abstract class Raffle {

    private String title;

    private String raffleGroup;

    private String sector;

    private Date raffleDate;

    public abstract void performRaffle();

}
