package com.tlndev.gamecenter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tlndev.gamecenter.domain.Mensagem;
import com.tlndev.gamecenter.domain.Usuario;

public interface MensagemRepository extends MongoRepository<Mensagem, String> {
    Mensagem findBy_id (String _id);
    List<Mensagem> findByTopico (String topico);
    List<Mensagem> findByUsuario(Usuario usuario);
}
