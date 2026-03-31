package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Avaliacao;
import com.projeto.sistema.modelos.Player;
import com.projeto.sistema.repositorios.avaliacaoRepositorio;
import com.projeto.sistema.repositorios.playerRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class avaliacaoControle {

    @Autowired
    private playerRepositorio playerRepositorio;

    @Autowired
    private avaliacaoRepositorio avaliacaoRepositorio;

    @GetMapping("/avaliacao")
    public ModelAndView avaliacao() {
        ModelAndView mv = new ModelAndView("administrativo/players/avaliacao");

        List<Player> players = playerRepositorio.findAll();
        mv.addObject("players", players);

        Map<Long, Avaliacao> avaliacoesMap = new HashMap<>();

        for (Player player : players) {
            List<Avaliacao> lista = avaliacaoRepositorio.findByPlayer(player);
            Avaliacao a = lista.isEmpty() ? new Avaliacao() : lista.get(0);
            avaliacoesMap.put(player.getId(), a);
        }

        mv.addObject("avaliacoesMap", avaliacoesMap);
        return mv;
    }

    @PostMapping("/avaliacao/salvar-todos")
    public ModelAndView salvarTodos(@RequestParam Map<String, String> params) {

        Map<Long, Avaliacao> avaliacoesMap = new HashMap<>();

        params.forEach((key, value) -> {

            if (key.startsWith("avaliacoes")) {

                Long playerId = Long.parseLong(
                        key.substring(key.indexOf("[") + 1, key.indexOf("]"))
                );

                String campo = key.substring(key.indexOf("].") + 2);

                avaliacoesMap.putIfAbsent(playerId, new Avaliacao());
                Avaliacao avaliacao = avaliacoesMap.get(playerId);

                Player player = playerRepositorio.findById(playerId).orElse(null);
                if (player != null) {
                    avaliacao.setPlayer(player);
                }

                switch (campo) {
                    case "comunicacao" -> avaliacao.setComunicacao(Integer.parseInt(value));
                    case "conhecimento" -> avaliacao.setConhecimento(Integer.parseInt(value));
                    case "faseRota" -> avaliacao.setFaseRota(Integer.parseInt(value));
                    case "fight" -> avaliacao.setFight(Integer.parseInt(value));
                    case "championPool" -> avaliacao.setChampionPool(Integer.parseInt(value));
                    case "anotacao" -> avaliacao.setAnotacao(value);
                }
            }
        });

        avaliacoesMap.forEach((playerId, avaliacao) -> {

            Player player = avaliacao.getPlayer();

            List<Avaliacao> existentes = avaliacaoRepositorio.findByPlayer(player);

            if (!existentes.isEmpty()) {
                avaliacao.setId(existentes.get(0).getId());
            }

            avaliacaoRepositorio.save(avaliacao);
        });

        return new ModelAndView("redirect:/avaliacao");
    }
}