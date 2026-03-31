package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Avaliacao;
import com.projeto.sistema.modelos.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface avaliacaoRepositorio extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByPlayer(Player player);
}