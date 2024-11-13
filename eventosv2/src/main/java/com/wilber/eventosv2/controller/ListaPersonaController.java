package com.wilber.eventosv2.controller;

import com.wilber.eventosv2.dto.EventoListaDTO;
import com.wilber.eventosv2.dto.ListaPersonaDTO;
import com.wilber.eventosv2.dto.PersonaDTO;
import com.wilber.eventosv2.service.ListaPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/listaPersonaApi")
public class ListaPersonaController {

    @Autowired
    ListaPersonaService listaPersonaService;


    @PostMapping("/evento/{eventoId}/asignarPersona/{personaId}/ALista/{listaId}")
    public ResponseEntity<ListaPersonaDTO> asignarPersonaLista(
            @PathVariable Integer eventoId,
            @PathVariable Integer personaId,
            @PathVariable Integer listaId

    ){
        ListaPersonaDTO listaPersonaDTOResponse = listaPersonaService.asignarPersonaLista(eventoId, listaId, personaId);
        return new ResponseEntity<>(listaPersonaDTOResponse, HttpStatus.CREATED);
    }

    //eliminarPersonaLista(Integer eventoId, Integer listaId, Integer personaId)

    @DeleteMapping("/evento/{eventoId}/eliminarPersona/{personaId}/DeLista/{listaId}")
    public ResponseEntity<Void> eliminarPersonaLista(
            @PathVariable Integer eventoId,
            @PathVariable Integer personaId,
            @PathVariable Integer listaId
    ){
        listaPersonaService.eliminarPersonaLista(eventoId, listaId, personaId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/evento/{eventoId}/personasDeLaLista/{listaId}")
    public ResponseEntity<List<PersonaDTO>> personasDeUnaLista(
            @PathVariable Integer eventoId,
            @PathVariable Integer listaId
    ){
        List<PersonaDTO> personaDTOList = listaPersonaService.personasDeUnaLista(eventoId, listaId);
        return new ResponseEntity<>(personaDTOList,HttpStatus.OK);
    }

    @GetMapping("/eventosYListas/dniPersona/{dniPersona}")
    public ResponseEntity<List<EventoListaDTO>> eventosYListasQuePertenecePersonaPorDNI(
            @PathVariable String dniPersona
    ){
        List<EventoListaDTO> eventoListaDTOList = listaPersonaService.eventosYListasQuePertenecePersonaPorDNI(dniPersona);
        return new ResponseEntity<>(eventoListaDTOList,HttpStatus.OK);
    }


}
