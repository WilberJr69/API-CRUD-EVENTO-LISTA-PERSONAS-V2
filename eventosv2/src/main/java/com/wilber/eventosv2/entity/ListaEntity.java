package com.wilber.eventosv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lista")
public class ListaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lista_id")
    private Integer listaId;

    @Column(name = "nombre_lista")
    private String nombreLista;

    @Column(name = "cantidad_maxima")
    private Integer cantidadMaxima;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventoEntity evento;


    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    private List<ListaPersonaEntity> personas;


}
