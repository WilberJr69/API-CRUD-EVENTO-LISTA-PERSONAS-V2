package com.wilber.eventosv2.mapper;

import com.wilber.eventosv2.dto.PersonaDTO;
import com.wilber.eventosv2.entity.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface PersonaMapper {

    PersonaDTO toPersonaDTO(PersonaEntity personaEntity);

    @Mapping(target = "personaId", ignore = true)
    PersonaEntity toPersonaEntity(PersonaDTO personaDTO);

}
