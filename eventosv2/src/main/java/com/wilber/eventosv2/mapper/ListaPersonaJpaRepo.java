package com.wilber.eventosv2.mapper;

import com.wilber.eventosv2.entity.ListaPersonaEntity;
import com.wilber.eventosv2.entity.ListaPersonaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaPersonaJpaRepo extends JpaRepository<ListaPersonaEntity, ListaPersonaId> {
}
