package com.tlndev.gamecenter.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tlndev.gamecenter.domain.Mensagem;
import com.tlndev.gamecenter.service.MensagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gamecenter")
public class MensagemController {
    @Autowired
    private MensagemService mensagemService;

    @RequestMapping(value = "/mensagens", method = RequestMethod.GET)
    public List<Mensagem> getAllMensagens() {
        return mensagemService.getAllMensagens();
    }
    
    @RequestMapping(value = "/comunidade/{idComunidade}/topico/{idTopico}/mensagens", method = RequestMethod.GET)
    public List<Mensagem> getMensagens(@PathVariable("idComunidade") String idComunidade, @PathVariable("idTopico") String idTopico) {
        return mensagemService.getMensagens(idComunidade, idTopico);
    }

    @RequestMapping(value = "/comunidade/{idComunidade}/topico/{idTopico}/mensagem/{id}", method = RequestMethod.GET)
    public Mensagem getMensagem(@PathVariable("idComunidade") String idComunidade, @PathVariable("idTopico") String idTopico, @PathVariable("id") String id) {
        return mensagemService.getMensagem(idComunidade, idTopico, id);
    }

//    @RequestMapping(value = "/{idUsuario}/mensagens", method = RequestMethod.GET)
//    public List<Mensagem> getMensagemByUsuario(@PathVariable("idUsuario") String idUsuario) {
//        return mensagemService.getMensagemByUsuario(idUsuario);
//    }

    @RequestMapping(value = "/comunidade/{idComunidade}/topico/{idTopico}/mensagem/{idUsuario}/post", method = RequestMethod.POST)
    public Mensagem criarMensagem(@PathVariable("idComunidade") String idComunidade, @PathVariable("idTopico") String idTopico, @PathVariable("idUsuario") String idUsuario, @Valid @RequestBody Mensagem mensagem) {
        return mensagemService.criarMensagem(idComunidade, idTopico, idUsuario, mensagem);
    }

    @RequestMapping(value = "/comunidade/{idComunidade}/topico/{idTopico}/mensagem/{id}/edit", method = RequestMethod.PUT)
    public Mensagem editarMensagem(@PathVariable("idComunidade") String idComunidade, @PathVariable("idTopico") String idTopico,
                                   @PathVariable("id") ObjectId id, @Valid @RequestBody Mensagem mensagem) {
        return mensagemService.editarMensagem(idComunidade, idTopico, id, mensagem);
    }

    @RequestMapping(value = "/comunidade/{idComunidade}/topico/{idTopico}/mensagem/{id}/delete", method = RequestMethod.DELETE)
    public void deletarMensagem(@PathVariable("idComunidade") String idComunidade, @PathVariable("idTopico") String idTopico, @PathVariable("id") ObjectId id) {
        mensagemService.deletarMensagem(idComunidade, idTopico, id);
    }

}
