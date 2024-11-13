package com.wilber.eventosv2.service;

import com.wilber.eventosv2.dto.ListaDTO;
import com.wilber.eventosv2.entity.EventoEntity;
import com.wilber.eventosv2.entity.ListaEntity;
import com.wilber.eventosv2.mapper.ListaMapper;
import com.wilber.eventosv2.repository.EventoJpaRepo;
import com.wilber.eventosv2.repository.ListaJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaService {

    @Autowired
    private ListaJpaRepo listaJpaRepo;

    @Autowired
    private EventoJpaRepo eventoJpaRepo;

    @Autowired
    private ListaMapper listaMapper;

    private EventoEntity eventoEncontrado(Integer eventoId){
        Optional<EventoEntity> evento = eventoJpaRepo.findById(eventoId);

        if (!evento.isPresent()){
            throw new RuntimeException("No existe el evento con el ID: "+eventoId);
        }

        return evento.get();

    }



    public EventoEntity getEventoByLista(Integer listaId){

        Optional<ListaEntity> listaOptional = listaJpaRepo.findById(listaId);
        if (!listaOptional.isPresent()){
            throw new RuntimeException("No existe la lista con el ID: "+listaId);
        }

        ListaEntity lista = listaOptional.get();
        EventoEntity evento = lista.getEvento();

        return evento;

    }

    public ListaDTO createLista(ListaDTO nuevaLista, Integer eventoId){

        EventoEntity evento = eventoEncontrado(eventoId);

        ListaEntity listaACrear = listaMapper.toListaEntity(nuevaLista);
        listaACrear.setEvento(evento);
        ListaEntity listaCreada = listaJpaRepo.save(listaACrear);

        return listaMapper.toListaDTO(listaCreada);

    }

    public List<ListaDTO> getListasByEvento(Integer eventoId){

        EventoEntity eventoEncontrado = eventoEncontrado(eventoId);
        List<ListaEntity> listasDelEvento = eventoEncontrado.getListas();

        return listasDelEvento.stream()
                .map(listaMapper::toListaDTO)
                .toList();


    }



    //El sistema debe permitir actualizar una lista (nombre y aforo)

    public ListaDTO editLista(Integer eventoId, Integer listaId, ListaDTO listaActualizada){

        EventoEntity evento = eventoEncontrado(eventoId);

        Optional<ListaEntity> listaOptional = listaJpaRepo.findById(listaId);

        if (!listaOptional.isPresent()){
            throw new RuntimeException("No existe la lista con el ID: "+listaId);
        }

        ListaEntity lista = listaOptional.get();

        if(!lista.getEvento().getEventoId().equals(eventoId)){
            throw new RuntimeException("La lista con el ID: "+listaId+" " +
                    "no pertenece al evento con el ID: "+eventoId);
        }

        lista.setNombreLista(listaActualizada.getNombreLista());
        lista.setCantidadMaxima(listaActualizada.getCantidadMaxima());

        return listaMapper.toListaDTO(listaJpaRepo.save(lista));



    }


    public void deleteLista(Integer eventoId, Integer listaId){

        EventoEntity evento = eventoEncontrado(eventoId);


        Optional<ListaEntity> listaOptional = listaJpaRepo.findById(listaId);
        if (!listaOptional.isPresent()){
            throw new RuntimeException("No existe la lista con el ID: "+ listaId);
        }

        ListaEntity lista = listaOptional.get();

        if (!lista.getEvento().getEventoId().equals(eventoId)){
            throw new RuntimeException("La lista con el ID: "+listaId+" no pertenece al evento con el ID: "+eventoId);
        }

        listaJpaRepo.deleteById(listaId);



    }

}

