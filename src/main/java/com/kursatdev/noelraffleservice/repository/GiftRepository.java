package com.kursatdev.noelraffleservice.repository;

import com.kursatdev.noelraffleservice.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift, Long> {
}
