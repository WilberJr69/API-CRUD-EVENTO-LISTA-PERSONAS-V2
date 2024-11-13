package com.wilber.eventosv2.mapper;

import com.wilber.eventosv2.dto.ListaDTO;
import com.wilber.eventosv2.entity.ListaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T03:30:22-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class ListaMapperImpl implements ListaMapper {

    @Override
    public ListaDTO toListaDTO(ListaEntity listaEntity) {
        if ( listaEntity == null ) {
            return null;
        }

        ListaDTO listaDTO = new ListaDTO();

        listaDTO.setNombreLista( listaEntity.getNombreLista() );
        listaDTO.setCantidadMaxima( listaEntity.getCantidadMaxima() );

        return listaDTO;
    }

    @Override
    public ListaEntity toListaEntity(ListaDTO listaDTO) {
        if ( listaDTO == null ) {
            return null;
        }

        ListaEntity listaEntity = new ListaEntity();

        listaEntity.setNombreLista( listaDTO.getNombreLista() );
        listaEntity.setCantidadMaxima( listaDTO.getCantidadMaxima() );

        return listaEntity;
    }
}
