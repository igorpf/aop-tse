/*
 * Copyright (c) 2016 Igor Pires Ferreira
 * ipferreira@inf.ufrgs.br
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 * In no event shall the author be liable for any claim or damages.
 *
 * Todos os direitos reservados.
 */
package com.gincaneiro.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author igor
 */
@Entity
@IdClass(TarefaId.class)
@Table(name="tarefa")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(nullable=false)
    private Long id;

    @Id
    @JoinColumn(nullable=false)
    @ManyToOne(cascade={CascadeType.DETACH})
    private Gincana gincana;
    
    @JoinColumn(nullable=false)
    @ManyToOne(cascade={CascadeType.DETACH})
    private Categoria categoria;
    
    @JoinColumn(nullable=false)
    @ManyToOne(cascade={CascadeType.DETACH})
    private Autor autor;
    
    @NotNull
    @Size(min=1)
    @Column(columnDefinition = "TEXT")
    private String nome;
    
    @NotNull
    @Size(min=1)
    @Column(columnDefinition = "TEXT")
    private String corpo;
    
    @NotNull
    @Size(min=1)
    @Column(columnDefinition = "TEXT")
    private String pontuacao;
    
    @NotNull
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime dataPostagem;
    
    private boolean visivel;
    
    public Tarefa(Long id, String nome, String corpo, String pontuacao, Gincana gincana, Autor autor, Categoria categoria,
                  boolean visivel, LocalDateTime dataPostagem){
        this.id = id;
        this.nome = nome;
        this.corpo = corpo;
        this.pontuacao = pontuacao;
        this.gincana = gincana;
        this.autor = autor;
        this.categoria = categoria;
        this.visivel=visivel;
        this.dataPostagem = dataPostagem;
    }

    public Tarefa(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarefa)) {
            return false;
        }
        Tarefa other = (Tarefa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome +" "+ id ;
    }

   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDateTime dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public Gincana getGincana() {
        return gincana;
    }

    public void setGincana(Gincana gincana) {
        this.gincana = gincana;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
}
