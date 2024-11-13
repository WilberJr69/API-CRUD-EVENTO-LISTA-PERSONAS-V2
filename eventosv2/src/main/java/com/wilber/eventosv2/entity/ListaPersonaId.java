package com.wilber.eventosv2.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class ListaPersonaId implements Serializable {

    private Integer listaId;
    private Integer personaId;

    public ListaPersonaId(Integer listaId, Integer personaId) {
        this.listaId = listaId;
        this.personaId = personaId;
    }

    public ListaPersonaId() {}

    // equals() y hashCode() (Obligatorio para claves compuestas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaPersonaId that = (ListaPersonaId) o;
        return Objects.equals(listaId, that.listaId) &&
                Objects.equals(personaId, that.personaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaId, personaId);
    }



}
