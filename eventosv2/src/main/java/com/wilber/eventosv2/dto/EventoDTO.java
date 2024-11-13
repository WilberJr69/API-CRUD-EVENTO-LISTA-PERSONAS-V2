package com.wilber.eventosv2.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
public class EventoDTO {

    private String nombre;
    private LocalDate dia;
    private Time hora;
    private String lugar;

}
