package com.wilber.eventosv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "evento")
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_id")
    private Integer eventoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dia")
    private LocalDate dia;

    @Column(name = "hora")
    private Time hora;

    @Column(name = "lugar")
    private String lugar;

    @OneToMany(mappedBy = "evento")
    private List<ListaEntity> listas;

}
