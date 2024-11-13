package com.wilber.eventosv2.repository;

import com.wilber.eventosv2.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaJpaRepo extends JpaRepository<PersonaEntity, Integer> {
}
