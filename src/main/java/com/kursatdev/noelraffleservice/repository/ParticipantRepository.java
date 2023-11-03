package com.kursatdev.noelraffleservice.repository;

import com.kursatdev.noelraffleservice.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
