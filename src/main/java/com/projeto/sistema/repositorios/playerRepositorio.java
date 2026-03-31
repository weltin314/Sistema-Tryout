package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface playerRepositorio extends JpaRepository<Player, Long> {
}
