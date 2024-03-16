package com.tlndev.gamecenter.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tlndev.gamecenter.domain.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

    Produto findBy_id(String _id);
    List<Produto> findByNickVendedor(String nickVendedor, Sort dataAnuncio);
    List<Produto> findByVendido(Boolean vendido, Sort dataAnuncio);
    List<Produto> findByNickVendedorAndVendido(String nickVendedor, Boolean vendido, Sort dataAnuncio);

    @Query("{ 'nome' : {$regex: ?0, $options: 'i'}, 'vendido' : false }")
    List<Produto> searchProdutos(String nome, Sort dataAnuncio);
}
