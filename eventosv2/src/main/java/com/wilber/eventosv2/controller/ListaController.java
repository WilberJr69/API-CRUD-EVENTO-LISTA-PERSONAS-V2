package com.wilber.eventosv2.controller;

import com.wilber.eventosv2.dto.EventoDTO;
import com.wilber.eventosv2.dto.ListaDTO;
import com.wilber.eventosv2.entity.EventoEntity;
import com.wilber.eventosv2.mapper.EventoMapper;
import com.wilber.eventosv2.mapper.ListaMapper;
import com.wilber.eventosv2.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listaApi")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private ListaMapper listaMapper;

    @GetMapping("/getEvento/lista/{listaId}")
    public ResponseEntity<EventoDTO> getEventoByLista(
            @PathVariable Integer listaId
    ){

        EventoEntity evento = listaService.getEventoByLista(listaId);
        EventoDTO eventoDTO = eventoMapper.toEventoDto(evento);

        return new ResponseEntity<>(eventoDTO, HttpStatus.OK);

    }



    @PostMapping("/createLista/evento/{eventoId}")
    public ResponseEntity<ListaDTO> crearLista(
            @RequestBody ListaDTO listaDTO,
            @PathVariable Integer eventoId
    ){

        ListaDTO lista = listaService.createLista(listaDTO,eventoId);

        return new ResponseEntity<>(lista,HttpStatus.CREATED);


    }


    @GetMapping("/getListas/evento/{eventoId}")
    public ResponseEntity<List<ListaDTO>> getListasByEvento(
            @PathVariable Integer eventoId
    ){
        List<ListaDTO> listaDTO = listaService.getListasByEvento(eventoId);
        return new ResponseEntity<>(listaDTO,HttpStatus.OK);
    }

    //El sistema debe permitir actualizar una lista (nombre y aforo)
    @PutMapping("/editLista/evento/{eventoId}/lista/{listaId}")
    public ResponseEntity<ListaDTO> editLista(
            @PathVariable Integer eventoId,
            @PathVariable Integer listaId,
            @RequestBody ListaDTO listaActualizada
    ){
        ListaDTO listaDTO = listaService.editLista(eventoId, listaId, listaActualizada);
        return new ResponseEntity<>(listaDTO,HttpStatus.OK);
    }

    @DeleteMapping("/deleteLista/evento/{eventoId}/lista/{listaId}")
    public ResponseEntity<Void> deleteLista(
            @PathVariable Integer eventoId,
            @PathVariable Integer listaId
    ){
        listaService.deleteLista(eventoId, listaId);
        return ResponseEntity.noContent().build();
    }


}
