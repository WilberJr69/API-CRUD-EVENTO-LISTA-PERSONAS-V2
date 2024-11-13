package com.wilber.eventosv2.mapper;

import com.wilber.eventosv2.dto.ListaDTO;
import com.wilber.eventosv2.entity.ListaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface ListaMapper {

    ListaDTO toListaDTO(ListaEntity listaEntity);
    @Mapping(target = "personas", ignore = true)
    @Mapping(target = "listaId", ignore = true)
    @Mapping(target = "evento", ignore = true)
    ListaEntity toListaEntity(ListaDTO listaDTO);

}
