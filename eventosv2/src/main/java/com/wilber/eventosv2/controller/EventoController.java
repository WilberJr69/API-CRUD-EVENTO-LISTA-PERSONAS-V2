package com.wilber.eventosv2.controller;

import com.wilber.eventosv2.dto.EventoDTO;
import com.wilber.eventosv2.entity.EventoEntity;
import com.wilber.eventosv2.mapper.EventoMapper;
import com.wilber.eventosv2.repository.EventoJpaRepo;
import com.wilber.eventosv2.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventoApi")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    @Autowired
    private EventoMapper eventoMapper;

    @GetMapping("/getAllEventos")
    public ResponseEntity<List<EventoDTO>> getAllEventos(){

        //transofrmar mediante un stream el entity usado para obtener los datos con JPA
        //y devolverlo como un List<EventoDTO>
        List<EventoDTO> eventos = eventoService.getAllEventos().stream()
                .map(eventoMapper::toEventoDto)
                .toList();

        return new ResponseEntity<>(eventos, HttpStatus.OK);

    }

    @PostMapping("/createEvento")
    public ResponseEntity<EventoDTO> createEvento(
            @RequestBody EventoDTO eventoDTO
    ){

        EventoEntity eventoEntity = eventoMapper.toEventoEntity(eventoDTO);
        EventoEntity eventoGuardado = eventoService.createEvento(eventoEntity);

        EventoDTO eventoResponse = eventoMapper.toEventoDto(eventoGuardado);

        return new ResponseEntity<>(eventoResponse,HttpStatus.CREATED);
    }

    @GetMapping("/getEvento/evento/{eventoId}")
    public ResponseEntity<EventoDTO> getEventoById(
            @PathVariable Integer eventoId
    ){

        Optional<EventoEntity> evento = eventoService.getEventoById(eventoId);
        if (!evento.isPresent()){
            throw new RuntimeException("No se encuentra el evento con el id: "+eventoId);
        }

        EventoEntity eventoEncontrado = evento.get();

        EventoDTO eventoDTO = eventoMapper.toEventoDto(eventoEncontrado);

        return new ResponseEntity<>(eventoDTO,HttpStatus.OK);



    }

    @PutMapping("/editEvento/evento/{eventoId}")
    public ResponseEntity<EventoDTO> editEvento(
        @RequestBody EventoDTO eventoActualizado,
        @PathVariable Integer eventoId
    ){
        EventoEntity evento = eventoMapper.toEventoEntity(eventoActualizado);
        eventoService.editEventoById(evento,eventoId);

        EventoDTO eventoDTO = eventoMapper.toEventoDto(evento);
        return new ResponseEntity<>(eventoDTO,HttpStatus.OK);

    }


    @DeleteMapping("/deleteEvento/evento/{eventoId}")
    public ResponseEntity<Void> deleteEvento(
            @PathVariable Integer eventoId
    ){
        eventoService.deleteEvento(eventoId);
        return ResponseEntity.noContent().build();
    }


}
