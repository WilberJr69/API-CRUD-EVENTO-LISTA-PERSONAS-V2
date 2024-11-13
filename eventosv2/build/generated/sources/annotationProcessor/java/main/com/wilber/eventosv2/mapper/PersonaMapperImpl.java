package com.wilber.eventosv2.mapper;

import com.wilber.eventosv2.dto.PersonaDTO;
import com.wilber.eventosv2.entity.PersonaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T03:30:22-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonaDTO toPersonaDTO(PersonaEntity personaEntity) {
        if ( personaEntity == null ) {
            return null;
        }

        PersonaDTO personaDTO = new PersonaDTO();

        personaDTO.setNombres( personaEntity.getNombres() );
        personaDTO.setApellidos( personaEntity.getApellidos() );
        personaDTO.setNumeroDni( personaEntity.getNumeroDni() );

        return personaDTO;
    }

    @Override
    public PersonaEntity toPersonaEntity(PersonaDTO personaDTO) {
        if ( personaDTO == null ) {
            return null;
        }

        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setNombres( personaDTO.getNombres() );
        personaEntity.setApellidos( personaDTO.getApellidos() );
        personaEntity.setNumeroDni( personaDTO.getNumeroDni() );

        return personaEntity;
    }
}
