package com.tlndev.gamecenter.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tlndev.gamecenter.domain.Topico;

public interface TopicoRepository extends MongoRepository<Topico, String> {
    Topico findBy_id(String _id);
    List<Topico> findByComunidade(String comunidade, Sort sort);

    @Query("{ 'comunidade' : {$regex: ?0, $options: 'i'}, 'titulo' : {$regex: ?1, $options: 'i'} }")
    List<Topico> searchTopicos(String comunidade, String titulo, Sort sort);
}
