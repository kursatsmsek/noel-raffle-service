package com.kursatdev.noelraffleservice.repository;

import com.kursatdev.noelraffleservice.model.GiftRaffle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRaffleRepository extends JpaRepository<GiftRaffle, Long> {
}
