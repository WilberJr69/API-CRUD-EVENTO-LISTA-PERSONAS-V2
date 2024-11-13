package com.wilber.eventosv2.service;

import com.wilber.eventosv2.dto.PersonaDTO;
import com.wilber.eventosv2.entity.PersonaEntity;
import com.wilber.eventosv2.mapper.PersonaMapper;
import com.wilber.eventosv2.repository.PersonaJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Autowired
    private PersonaJpaRepo personaJpaRepo;

    @Autowired
    private PersonaMapper personaMapper;

    public PersonaDTO createPersona(PersonaDTO nuevaPersona){
        PersonaEntity persona = personaJpaRepo.save(
                personaMapper.toPersonaEntity(nuevaPersona));

        return personaMapper.toPersonaDTO(persona);
    }


}
