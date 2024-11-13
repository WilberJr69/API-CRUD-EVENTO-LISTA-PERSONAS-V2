package com.wilber.eventosv2.mapper;

import com.wilber.eventosv2.dto.EventoDTO;
import com.wilber.eventosv2.entity.EventoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T03:30:22-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class EventoMapperImpl implements EventoMapper {

    @Override
    public EventoDTO toEventoDto(EventoEntity eventoEntity) {
        if ( eventoEntity == null ) {
            return null;
        }

        EventoDTO eventoDTO = new EventoDTO();

        eventoDTO.setNombre( eventoEntity.getNombre() );
        eventoDTO.setDia( eventoEntity.getDia() );
        eventoDTO.setHora( eventoEntity.getHora() );
        eventoDTO.setLugar( eventoEntity.getLugar() );

        return eventoDTO;
    }

    @Override
    public EventoEntity toEventoEntity(EventoDTO eventoDTO) {
        if ( eventoDTO == null ) {
            return null;
        }

        EventoEntity eventoEntity = new EventoEntity();

        eventoEntity.setNombre( eventoDTO.getNombre() );
        eventoEntity.setDia( eventoDTO.getDia() );
        eventoEntity.setHora( eventoDTO.getHora() );
        eventoEntity.setLugar( eventoDTO.getLugar() );

        return eventoEntity;
    }
}
