package com.wilber.eventosv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Integer personaId;

    private String nombres;
    private String apellidos;

    @Column(name = "numero_dni")
    private String numeroDni;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<ListaPersonaEntity> listas;



}
