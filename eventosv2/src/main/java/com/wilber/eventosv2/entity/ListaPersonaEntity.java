package com.wilber.eventosv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lista_personas")
public class ListaPersonaEntity {

    @EmbeddedId
    private ListaPersonaId listaPersonaId;

    @ManyToOne
    @MapsId("listaId")
    @JoinColumn(name = "lista_id")
    private ListaEntity lista;

    @ManyToOne
    @MapsId("personaId")
    @JoinColumn(name = "persona_id")
    private PersonaEntity persona;


    public ListaPersonaEntity() {}

    public ListaPersonaEntity(ListaEntity lista, PersonaEntity persona) {
        this.listaPersonaId = new ListaPersonaId(lista.getListaId(), persona.getPersonaId());
        this.lista = lista;
        this.persona = persona;
    }


}
