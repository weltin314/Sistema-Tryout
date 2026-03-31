package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Player;
import com.projeto.sistema.repositorios.avaliacaoRepositorio;
import com.projeto.sistema.repositorios.playerRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class playerControle {

    @Autowired
    private playerRepositorio playerRepositorio;

    @Autowired
    private avaliacaoRepositorio avaliacaoRepositorio;

    @GetMapping("/cadastroPlayer")
    public ModelAndView cadastrar(Player player) {
        ModelAndView mv = new ModelAndView("administrativo/players/cadastro");
        mv.addObject("player", player);
        return mv;
    }

    @GetMapping("/listarPlayer")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/players/lista");
        mv.addObject("players", playerRepositorio.findAll());
        return mv;
    }

    @GetMapping("/deletarPlayer/{id}")
    public String deletar(@PathVariable("id") Long id) {
        Player player = playerRepositorio.findById(id).orElse(null);
        if(player != null) {

            avaliacaoRepositorio.deleteAll(avaliacaoRepositorio.findByPlayer(player));

            playerRepositorio.delete(player);
        }
        return "redirect:/listarPlayer";
    }

    @GetMapping("/editarPlayer/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Player> player = playerRepositorio.findById(id);
        return cadastrar(player.get());
    }

    @PostMapping("/salvarPlayer")
    public ModelAndView salvar(Player player, BindingResult result) {
        if(result.hasErrors()){
            return cadastrar(player);
        }
        playerRepositorio.saveAndFlush(player);
        return cadastrar(new Player());
    }
}