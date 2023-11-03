package com.kursatdev.noelraffleservice.model;

public abstract class Raffle {

    private String title;

    private String group;

    private String sector;

    private Participant[] participants;

    public abstract void performRaffle();

}
