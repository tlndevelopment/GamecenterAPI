package com.tlndev.gamecenter.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tlndev.gamecenter.domain.Jogo;

public interface JogoRepository extends MongoRepository<Jogo, String> {

    Jogo findBy_id(String _id);

    @Query("{ 'titulo' : {$regex: ?0, $options: 'i'} }")
    List<Jogo> searchJogos(String pesquisa, Sort sort);
}
