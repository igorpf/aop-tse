/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gincaneiro.services;

import com.gincaneiro.entities.Categoria;
import com.gincaneiro.entities.Gincana;
import com.gincaneiro.entities.Tarefa;
import com.gincaneiro.repositories.TarefaRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author igor
 */
@Service
public class TarefaService extends GenericService<Tarefa> {

    @Autowired
    private TarefaRepository repo;

    @Override
    public JpaRepository<Tarefa, Long> getRepository() {
        return repo;
    }

    @Override
    public Tarefa get(Long id) {
        throw new UnsupportedOperationException();
    }

    public Tarefa get(Long gincanaId, Long tarefaId) {
        return repo.findByGincanaIdAndId(gincanaId, tarefaId);
    }

    @Override
    public void save(Tarefa t) {
        throw new UnsupportedOperationException();
    }

    public void saveTarefa(Tarefa t) throws Exception {
        if (repo.existsByGincanaIdAndId(t.getGincana().getId(),t.getId())) {
            throw new Exception("Já existe uma tarefa cadastrada com esse id");
        }
        repo.save(t);

    }

    public List<Tarefa> findAll(Long id) {
        return repo.findByGincanaIdOrderByDataPostagemDesc(id);
    }

    public List<Tarefa> findAllVisible(Long id) {
        return repo.findByVisivelAndGincanaId(true, id);
    }

    public List<Tarefa> findAllVisible(Long id, Pageable p) {
        return repo.findByVisivelAndGincanaIdOrderByDataPostagemDesc(true, id, p);
    }

    public Map<Categoria, Integer> countByCategoria(Gincana g) {
        List<Object[]> l = repo.countCategoriaByGincana(g);
        Map<Categoria, Integer> m = new HashMap<>();

        l.parallelStream().forEach(a -> {
            m.put((Categoria) a[0], ((Long) a[1]).intValue());
        });

        return m;
    }
    public boolean exists(Long gincanaId, Long id) {
        return repo.existsByGincanaIdAndId(gincanaId, id);
    }
}
