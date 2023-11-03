package com.kursatdev.noelraffleservice.dto;

import com.kursatdev.noelraffleservice.model.Participant;
import lombok.Data;

import java.util.List;

@Data
public class NoelRaffleData {
    private String title;
    private String group;
    private String sector;
    private List<Participant> participants;
}
