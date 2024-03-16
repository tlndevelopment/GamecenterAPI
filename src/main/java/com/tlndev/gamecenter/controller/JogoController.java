package com.tlndev.gamecenter.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tlndev.gamecenter.domain.Jogo;
import com.tlndev.gamecenter.service.JogoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gamecenter")
public class JogoController {
    @Autowired
    private JogoService jogoService;

    @RequestMapping(value = "/jogos", method = RequestMethod.GET)
    public List<Jogo> getJogos() {
        return jogoService.getJogos();
    }

    @RequestMapping(value = "/pesquisar/jogos/q={pesquisa}", method = RequestMethod.GET)
    public List<Jogo> searchJogos(@PathVariable("pesquisa") String pesquisa) {
        return jogoService.searchJogos(pesquisa);
    }

    @RequestMapping(value = "/jogos/ranking-midia", method = RequestMethod.GET)
    public List<Jogo> getJogosRankingMidia() {
        return jogoService.getJogosRankingMidia();
    }

    @RequestMapping(value = "/jogos/ranking-usuarios", method = RequestMethod.GET)
    public List<Jogo> getJogosRankingUsuarios() {
        return jogoService.getJogosRankingUsuarios();
    }

    @RequestMapping(value = "/jogo/{id}", method = RequestMethod.GET)
    public Jogo getJogo(@PathVariable("id") String id) {
        return jogoService.getJogo(id);
    }

    @RequestMapping(value = "/jogo/post", method = RequestMethod.POST)
    public Jogo postJogo(@Valid @RequestBody Jogo jogo) {
        return jogoService.postJogo(jogo);
    }
    
    @RequestMapping(value = "/jogo/{id}/delete", method = RequestMethod.DELETE)
    public void deleteJogo(@PathVariable("id") ObjectId id) {
        jogoService.deletarJogo(id);
    }

    @RequestMapping(value = "/jogo/{id}/dar-nota", method = RequestMethod.PUT)
    public Jogo darNotaJogo(@PathVariable("id") ObjectId id, @Valid @RequestBody Jogo jogo) {
        return jogoService.darNotaJogo(id, jogo);
    }

}
