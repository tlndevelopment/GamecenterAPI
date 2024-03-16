package com.tlndev.gamecenter.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tlndev.gamecenter.domain.Comunidade;
import com.tlndev.gamecenter.service.ComunidadeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/gamecenter")
public class ComunidadeController {
    @Autowired
    private ComunidadeService comunidadeService;

    @RequestMapping(value = "/comunidades", method = RequestMethod.GET)
    public List<Comunidade> getComunidades() {
        return comunidadeService.getComunidades();
    }

    @RequestMapping(value = "/pesquisar/comunidades/q={pesquisa}", method = RequestMethod.GET)
    public List<Comunidade> searchComunidades(@PathVariable("pesquisa") String pesquisa) {
        return comunidadeService.searchComunidades(pesquisa);
    }

    @RequestMapping(value = "/comunidade/{id}", method = RequestMethod.GET)
    public Comunidade getComunidade(@PathVariable("id") ObjectId id) {
        return comunidadeService.getComunidade(id.toHexString());
    }

    @RequestMapping(value = "/comunidade/criar", method = RequestMethod.POST)
    public Comunidade criarComunidade(@Valid @RequestBody Comunidade comunidade) {
        return comunidadeService.criarComunidade(comunidade);
    }

    @RequestMapping(value = "/comunidade/{id}/edit", method = RequestMethod.PUT)
    public Comunidade editarComunidade(@PathVariable("id") ObjectId id, @Valid @RequestBody Comunidade comunidade) {
        return comunidadeService.editarComunidade(id, comunidade);
    }

    @RequestMapping(value = "/comunidade/{id}/delete", method = RequestMethod.DELETE)
    public void deletarComunidade(@PathVariable("id") ObjectId id) {
        comunidadeService.deletarComunidade(id);
    }
}
