package com.wilber.eventosv2.service;

import com.wilber.eventosv2.dto.EventoListaDTO;
import com.wilber.eventosv2.dto.ListaPersonaDTO;
import com.wilber.eventosv2.dto.PersonaDTO;
import com.wilber.eventosv2.entity.*;
import com.wilber.eventosv2.mapper.ListaPersonaJpaRepo;
import com.wilber.eventosv2.repository.EventoJpaRepo;
import com.wilber.eventosv2.repository.ListaJpaRepo;
import com.wilber.eventosv2.repository.PersonaJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListaPersonaService {

    @Autowired
    private ListaPersonaJpaRepo listaPersonaJpaRepo;

    @Autowired
    private EventoJpaRepo eventoJpaRepo;

    @Autowired
    private ListaJpaRepo listaJpaRepo;

    @Autowired
    private PersonaJpaRepo personaJpaRepo;


    public ListaPersonaDTO asignarPersonaLista(Integer eventoId, Integer listaId, Integer personaId){

        Optional<ListaEntity> optionalLista = listaJpaRepo.findById(listaId);
        if(!optionalLista.isPresent()){
            throw new RuntimeException("No existe esa lista con el ID: "+listaId);
        }

        Optional<EventoEntity> optionalEvento = eventoJpaRepo.findById(eventoId);
        if(!optionalEvento.isPresent()){
            throw new RuntimeException("La lista si exite, pero no existe ese evento con el ID: "+eventoId);
        }

        ListaEntity lista = optionalLista.get();
        //       EventoEntity evento = optionalEvento.get();

        if (!lista.getEvento().getEventoId().equals(eventoId)){
            throw new RuntimeException("No se puede asignar la persona a la lista, " +
                    "porque dicha lista con ID"+listaId+
                    "no pertenece al evento con ID: "+eventoId);
        }



        Optional<PersonaEntity> optionalPersona = personaJpaRepo.findById(personaId);
        if (!optionalPersona.isPresent()){
            throw new RuntimeException("No existe el id de la persona: "+personaId);
        }

        Integer aforoMaximoLista = lista.getCantidadMaxima();
        Integer aforoLLenado = lista.getPersonas().size();
        if (aforoMaximoLista.equals(aforoLLenado)){
            throw new RuntimeException
                    ("Se lleno el aforo, no se pudo agregar a la persona con ID: "+personaId+" en la lista con ID: "+listaId);
        }


        PersonaEntity persona = optionalPersona.get();


        ListaPersonaEntity listaPersonaEntity;
        listaPersonaEntity = new ListaPersonaEntity();

        listaPersonaEntity.setLista(lista);
        listaPersonaEntity.setPersona(persona);

        ListaPersonaId listaPersonaId = new ListaPersonaId(lista.getListaId(),persona.getPersonaId());

        listaPersonaEntity.setListaPersonaId(listaPersonaId);

/*
        try {
            ListaPersonaEntity listaPersonaEntity11 = listaPersonaJpaRepo.save(listaPersonaEntity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Guardando la entidad: " + listaPersonaEntity);
            throw new RuntimeException("Error al guardar ListaPersonaEntity: " + e.getMessage());
        }
*/
        ListaPersonaEntity listaPersonaEntity1 = listaPersonaJpaRepo.save(listaPersonaEntity);



        ListaPersonaDTO listaPersonaDTORespuesta;
        listaPersonaDTORespuesta = new ListaPersonaDTO();

        listaPersonaDTORespuesta.setPersonaId(listaPersonaEntity1.getPersona().getPersonaId());
        listaPersonaDTORespuesta.setListaId(listaPersonaEntity1.getLista().getListaId());

        return listaPersonaDTORespuesta;


    }

    public void eliminarPersonaLista(Integer eventoId, Integer listaId, Integer personaId){

        Optional<EventoEntity> optionalEvento = eventoJpaRepo.findById(eventoId);

        if (optionalEvento.isEmpty()) throw new RuntimeException
                ("No existe el evento con el ID: "+eventoId);

        Optional<ListaEntity> optionalLista = listaJpaRepo.findById(listaId);
        if (optionalLista.isEmpty()) throw new RuntimeException
                ("No existe la lista con el ID: "+listaId);


        Optional<PersonaEntity> optionalPersona = personaJpaRepo.findById(personaId);
        if (optionalPersona.isEmpty()) {
            throw new RuntimeException("No existe la persona con el ID: " + personaId);
        }

        ListaEntity lista = optionalLista.get();
        if (!lista.getEvento().getEventoId().equals(eventoId)) throw new RuntimeException
                ("La lista con ID: "+listaId+" no pertenece al evento con el ID: "+eventoId);


        // Buscar la relación existente en la tabla intermedia
        ListaPersonaId listaPersonaId = new ListaPersonaId(listaId, personaId);
        Optional<ListaPersonaEntity> optionalListaPersona = listaPersonaJpaRepo.findById(listaPersonaId);
        if (optionalListaPersona.isEmpty()) {
            throw new RuntimeException("La persona con ID: " + personaId + " no esta asignada a la lista con ID: " + listaId);
        }

        // Eliminar la relación
        listaPersonaJpaRepo.delete(optionalListaPersona.get());

    }

    public List<PersonaDTO> personasDeUnaLista(Integer eventoId, Integer listaId){
        Optional<EventoEntity> optionalEvento = eventoJpaRepo.findById(eventoId);

        if (optionalEvento.isEmpty()) throw new RuntimeException
                ("No existe el evento con el ID: "+eventoId);

        Optional<ListaEntity> optionalLista = listaJpaRepo.findById(listaId);
        if (optionalLista.isEmpty()) throw new RuntimeException
                ("No existe la lista con el ID: "+listaId);


        ListaEntity lista = optionalLista.get();
        if (!lista.getEvento().getEventoId().equals(eventoId)) throw new RuntimeException
                ("La lista con ID: "+listaId+" no pertenece al evento con el ID: "+eventoId);


        List<ListaPersonaEntity> listaPersonaEntityList = lista.getPersonas();

        // Transformar las entidades en DTOs
        List<PersonaDTO> personaDTOs = listaPersonaEntityList.stream()
                .map(listaPersona -> {
                    PersonaDTO personaDTO = new PersonaDTO();
                    PersonaEntity persona = listaPersona.getPersona();
                    personaDTO.setNombres(persona.getNombres());
                    personaDTO.setApellidos(persona.getApellidos());
                    personaDTO.setNumeroDni(persona.getNumeroDni());
                    return personaDTO;
                })
                .collect(Collectors.toList());

        return personaDTOs;



    }

    public List<EventoListaDTO> eventosYListasQuePertenecePersonaPorDNI(String dniPersona){

        List<PersonaEntity> listaPersonas = personaJpaRepo.findAll();

        Optional<PersonaEntity> personaOptional = listaPersonas.stream().filter(personaEntity -> {
            return personaEntity.getNumeroDni().equals(dniPersona);
        }).findFirst();

        if(personaOptional.isEmpty()){
            throw new RuntimeException("Esa persona con el dni: "+dniPersona+" no está registrado");
        }

        PersonaEntity persona = personaOptional.get();

        // Obtener todas las relaciones de lista-persona y filtrar por la persona
        List<ListaPersonaEntity> listaPersonaEntities = listaPersonaJpaRepo.findAll();
        List<ListaPersonaEntity> relacionesFiltradas = listaPersonaEntities.stream()
                .filter(listaPersona -> listaPersona.getPersona().getPersonaId().equals(persona.getPersonaId()))
                .collect(Collectors.toList());

        // Mapear a EventoListaDTO
        List<EventoListaDTO> eventoListaDTOs = relacionesFiltradas.stream()
                .map(listaPersona -> {
                    EventoListaDTO dto = new EventoListaDTO();
                    dto.setNombrePersona(persona.getNombres() + " " + persona.getApellidos());
                    dto.setNumeroDni(persona.getNumeroDni());
                    dto.setNombreLista(listaPersona.getLista().getNombreLista());
                    dto.setNombreEvento(listaPersona.getLista().getEvento().getNombre()); // Asegúrate de tener acceso al nombre del evento
                    return dto;
                })
                .collect(Collectors.toList());

        return eventoListaDTOs;




    }





}
