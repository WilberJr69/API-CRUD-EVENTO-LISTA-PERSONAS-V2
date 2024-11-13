package com.wilber.eventosv2.repository;

import com.wilber.eventosv2.entity.ListaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaJpaRepo extends JpaRepository<ListaEntity, Integer> {



}
