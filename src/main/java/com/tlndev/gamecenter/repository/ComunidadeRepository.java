package com.tlndev.gamecenter.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tlndev.gamecenter.domain.Comunidade;

public interface ComunidadeRepository extends MongoRepository<Comunidade, String> {
    Comunidade findBy_id(String _id);
    Comunidade findByNome(String nome);

    @Query("{ 'nome' : {$regex: ?0, $options: 'i'} }")
    List<Comunidade> searchComunidades(String nome, Sort sort);
}
