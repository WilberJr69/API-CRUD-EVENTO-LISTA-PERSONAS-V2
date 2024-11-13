package com.wilber.eventosv2.mapper;

import com.wilber.eventosv2.dto.EventoDTO;
import com.wilber.eventosv2.entity.EventoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    EventoDTO toEventoDto(EventoEntity eventoEntity);
    @Mapping(target = "eventoId", ignore = true)
    @Mapping(target = "listas", ignore = true)
    EventoEntity toEventoEntity(EventoDTO eventoDTO);

}
