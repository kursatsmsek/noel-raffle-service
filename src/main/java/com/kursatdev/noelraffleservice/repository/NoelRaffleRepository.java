package com.kursatdev.noelraffleservice.repository;

import com.kursatdev.noelraffleservice.model.NoelRaffle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoelRaffleRepository extends JpaRepository<NoelRaffle, Long> {
}
