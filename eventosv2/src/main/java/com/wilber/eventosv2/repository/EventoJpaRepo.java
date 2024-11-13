package com.wilber.eventosv2.repository;

import com.wilber.eventosv2.entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoJpaRepo extends JpaRepository<EventoEntity, Integer> {



}
