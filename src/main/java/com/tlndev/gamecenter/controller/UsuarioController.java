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
import com.tlndev.gamecenter.domain.Usuario;
import com.tlndev.gamecenter.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gamecenter")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @RequestMapping(value = "/pesquisar/usuarios/q={pesquisa}", method = RequestMethod.GET)
    public List<Usuario> searchUsuarios(@PathVariable("pesquisa") String pesquisa) {
        return usuarioService.searchUsuarios(pesquisa);
    }

    @RequestMapping(value = "/usuario/{nick}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable("nick") String nick) {
        return usuarioService.getUsuario(nick);
    }

    @RequestMapping(value = "/usuario/{nick}/{senha}/logar", method = RequestMethod.GET)
    public Usuario logarUsuario(@PathVariable("nick") String nick, @PathVariable("senha") String senha) {
        return usuarioService.logarUsuario(nick, senha);
    }

    @RequestMapping(value = "/usuario/registrar", method = RequestMethod.POST)
    public Usuario criarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @RequestMapping(value = "/{nick}/addJogoBoblioteca/{idJogo}", method = RequestMethod.PUT)
    public Jogo addJogoBiblioteca(@PathVariable("idJogo") ObjectId idJogo, @PathVariable("nick") String nick, @Valid @RequestBody Jogo jogo) {
        return usuarioService.addJogoBiblioteca(nick, idJogo, jogo);
    }

    @RequestMapping(value = "/usuario/{id}/edit", method = RequestMethod.PUT)
    public Usuario editarUsuario(@PathVariable("id") ObjectId id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.editarUsuario(id, usuario);
    }

    @RequestMapping(value = "/usuario/{id}/delete", method = RequestMethod.DELETE)
    public void deletarUsuario(@PathVariable("id") ObjectId id) {
        usuarioService.deletarUsuario(id);
    }

}