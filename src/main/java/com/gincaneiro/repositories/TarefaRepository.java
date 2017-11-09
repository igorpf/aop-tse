/*
 * Copyright (c) 2016 Igor Pires Ferreira
 * ipferreira@inf.ufrgs.br
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 * In no event shall the author be liable for any claim or damages.
 *
 * Todos os direitos reservados.
 */
package com.gincaneiro.repositories;

import com.gincaneiro.entities.Gincana;
import com.gincaneiro.entities.Tarefa;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author igor
 */
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    /**
     *  Busca uma tarefa de uma determinada gincana
     * @param gid id da gincana
     * @param id id da tarefa
     * @return uma tarefa, caso encontrada, null, caso contrário.
     */
    Tarefa findByGincanaIdAndId(Long gid, Long id);
    
    /**
     * Retorna todas as tarefas visiveis da gincana
     *
     * @param visivel
     * @param id
     * @return
     */
    List<Tarefa> findByVisivelAndGincanaId(boolean visivel, Long id);

    /**
     * Retorna todas as tarefas visíveis de uma gincana em ordem pela data de
     * postagem
     *
     * @param visivel
     * @param id
     * @return
     */
    List<Tarefa> findByVisivelAndGincanaIdOrderByDataPostagemDesc(boolean visivel, Long id);

    /**
     * Retorna todas as tarefas visíveis de uma gincana em ordem pela data de
     * postagem
     *
     * @param visivel Visibilidade da tarefa
     * @param id Id da gincana
     * @param page página de tarefas
     * @return
     */
    List<Tarefa> findByVisivelAndGincanaIdOrderByDataPostagemDesc(boolean visivel, Long id, Pageable page);

    /**
     * Retorna todas as tarefas de uma gincana em ordem pela data de postagem
     *
     * @param id
     * @return Lista de tarefas que atendem ao critério
     */
    List<Tarefa> findByGincanaIdOrderByDataPostagemDesc(Long id);
    
    /**
     * Conta a quantidade de tarefas por categoria de uma determinada gincana
     * 
     * @param g Gincana que possui as tarefas a serem contadas
     * @return Retorna uma lista de arrays de 2 posições, onde a primeira é a categoria de tarefas
     * e a segunda é a quantidade de tarefas daquela categoria na gincana 
     */
    @Query("SELECT c, COUNT(DISTINCT c) "
           + "FROM Tarefa t "
           + "JOIN t.categoria c "
          + "WHERE t.categoria=c AND t.gincana=:g "
       + "GROUP BY c.id")
    List<Object[]> countCategoriaByGincana(@Param("g")Gincana g);
    
    /**
     * Conta quantas tarefas existem com o id determinado na gincana especificada
     * @param gid id da gincana
     * @param id id da tarefa
     * @return true, caso exista, false, caso contrário.
     */
    @Query("SELECT count(t)>0 " +
            " FROM Tarefa t " +
            "WHERE t.gincana.id = :gid AND t.id =:id")
    boolean existsByGincanaIdAndId(@Param("gid") Long gid, @Param("id") Long id);
}
