package com.tlndev.gamecenter.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tlndev.gamecenter.domain.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findBy_id(String _id);
    Usuario findByNick(String nick);
    Usuario findByNickAndSenha(String nick, String senha);
    Usuario findByEmailAndSenha(String email, String senha);

    @Query("{ 'nick' : {$regex: ?0, $options: 'i'} }")
    List<Usuario> searchUsuarios(String nick, Sort sort);
}
