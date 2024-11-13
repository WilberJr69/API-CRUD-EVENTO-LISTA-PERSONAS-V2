package com.wilber.eventosv2.controller;

import com.wilber.eventosv2.dto.PersonaDTO;
import com.wilber.eventosv2.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personaApi")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/createPersona")
    public ResponseEntity<PersonaDTO> createPersona(
            @RequestBody PersonaDTO personaDTO
    ){
        PersonaDTO persona = personaService.createPersona(personaDTO);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }









}
