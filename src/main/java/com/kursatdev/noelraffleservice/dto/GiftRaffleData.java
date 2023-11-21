package com.kursatdev.noelraffleservice.dto;

import com.kursatdev.noelraffleservice.model.Gift;
import com.kursatdev.noelraffleservice.model.Participant;
import lombok.Data;

import java.util.List;

@Data
public class GiftRaffleData {
    private String title;
    private String group;
    private String sector;
    private List<Participant> participants;
    private List<Gift> gifts;
}
