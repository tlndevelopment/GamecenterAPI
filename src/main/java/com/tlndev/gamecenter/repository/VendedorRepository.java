package com.tlndev.gamecenter.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tlndev.gamecenter.domain.Vendedor;

public interface VendedorRepository  extends MongoRepository<Vendedor, String> {
    Vendedor findBy_id(String _id);

    @Query("{ 'usuario.nick' : ?0 }")
    Vendedor findByUsuario(String usuario);

    @Query("{ 'usuario.nick' : {$regex: ?0, $options: 'i'} }")
    List<Vendedor> searchVendedores(String nick, Sort sort);

    @Query("{ 'vendas' : { $gt: 0 } }")
    List<Vendedor> rankingVendedores(Sort notaVendedor);
}
