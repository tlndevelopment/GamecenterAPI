package com.tlndev.gamecenter.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tlndev.gamecenter.domain.Topico;
import com.tlndev.gamecenter.service.TopicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gamecenter")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @RequestMapping(value = "/topicos", method = RequestMethod.GET)
    public List<Topico> getAllTopicos() {
        return topicoService.getAllTopicos();
    }
    
    @RequestMapping(value = "/comunidade/{idComunidade}/topicos", method = RequestMethod.GET)
    public List<Topico> getTopicos(@PathVariable("idComunidade") String idComunidade) {
        return topicoService.getTopicos(idComunidade);
    }

    @RequestMapping(value = "/comunidade/{idComunidade}/topico/{id}", method = RequestMethod.GET)
    public Topico getTopicos(@PathVariable("idComunidade") String idComunidade, @PathVariable("id") String id) {
        return topicoService.getTopico(idComunidade, id);
    }

    @RequestMapping(value = "/comunidade/{idComunidade}/pesquisar/topicos/q={pesquisa}", method = RequestMethod.GET)
    public List<Topico> searchTopicos(@PathVariable("idComunidade") String idComunidade, @PathVariable("pesquisa") String pesquisa) {
        return topicoService.searchTopicos(idComunidade, pesquisa);
    }

    @RequestMapping(value = "/comunidade/{idComunidade}/topico/criar", method = RequestMethod.POST)
    public Topico criarTopico(@PathVariable("idComunidade") String idComunidade, @Valid @RequestBody Topico topico) {
        return topicoService.criarTopico(idComunidade, topico);
    }

    @RequestMapping(value = "/comunidade/{idComunidade}/topico/{id}/edit", method = RequestMethod.PUT)
    public Topico editarTopico(@PathVariable("idComunidade") String idComunidade, @PathVariable("id") ObjectId id, @Valid @RequestBody Topico topico) {
        return topicoService.editarTopico(idComunidade, id, topico);
    }

    @RequestMapping(value = "/comunidade/{idComunidade}/topico/{id}/delete", method = RequestMethod.DELETE)
    public void deletarTopico(@PathVariable("idComunidade") String idComunidade, @PathVariable("id") ObjectId id) {
        topicoService.deletarTopico(idComunidade, id);
    }

}
