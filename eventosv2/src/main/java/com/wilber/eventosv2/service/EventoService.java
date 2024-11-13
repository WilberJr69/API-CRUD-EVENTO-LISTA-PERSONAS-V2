package com.wilber.eventosv2.service;

import com.wilber.eventosv2.entity.EventoEntity;
import com.wilber.eventosv2.repository.EventoJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoJpaRepo eventoJpaRepo;

    public List<EventoEntity> getAllEventos(){
        return eventoJpaRepo.findAll();
    }

    public EventoEntity createEvento(EventoEntity evento){
        return eventoJpaRepo.save(evento);
    }

    public Optional<EventoEntity> getEventoById(Integer eventoId){
        return eventoJpaRepo.findById(eventoId);
    }

    public EventoEntity editEventoById(EventoEntity eventoActualizado, Integer eventoId){

        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);
        if (!eventoOptional.isPresent()){
            throw new RuntimeException("No se encuentra el evento con el id: "+eventoId);
        }

        EventoEntity eventoExistente = eventoOptional.get();

        eventoExistente.setNombre(eventoActualizado.getNombre());
        eventoExistente.setDia(eventoActualizado.getDia());
        eventoExistente.setHora(eventoActualizado.getHora());
        eventoExistente.setLugar(eventoActualizado.getLugar());

        return eventoJpaRepo.save(eventoExistente);


    }


    public void deleteEvento(Integer eventoId){
        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);
        if (!eventoOptional.isPresent()){
            throw new RuntimeException("No se encuentra el evento con el id: "+eventoId);
        }

        eventoJpaRepo.deleteById(eventoId);

    }







}
