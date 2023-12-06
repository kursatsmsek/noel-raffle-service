package com.kursatdev.noelraffleservice.service;

import com.kursatdev.noelraffleservice.model.Gift;
import com.kursatdev.noelraffleservice.repository.GiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;

    public Gift getGiftById(Long id) {
        return giftRepository.findById(id).orElseThrow();
    }
}
