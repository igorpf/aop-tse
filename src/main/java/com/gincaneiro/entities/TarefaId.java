package com.gincaneiro.entities;

import java.io.Serializable;

/**
 * Created by igor on 05/03/17.
 */
public class TarefaId implements Serializable{

    private Long id;
    private Long gincana;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGincana() {
        return gincana;
    }

    public void setGincana(Long gincana) {
        this.gincana = gincana;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TarefaId) {
            TarefaId that = (TarefaId) obj;
            return this.gincana == that.gincana && this.id == that.id;
        }
        else
            return false;
    }
}
