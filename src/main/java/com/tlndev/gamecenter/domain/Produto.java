package com.tlndev.gamecenter.domain;

import java.util.Date;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Document(collection = "Produto")
public class Produto {
	@Id
    private ObjectId _id;

    private String nickVendedor;
    private String nome;
    private String descricao;
    private Double valor;
    private Double frete;
    private String imagem;
    private Boolean vendido;
    private String cmmComprador;
    private Double notaVenda;
    private Date dataAnuncio;

    public Produto() {

    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return get_id().equals(produto.get_id()) &&
                getNickVendedor().equals(produto.getNickVendedor()) &&
                getNome().equals(produto.getNome()) &&
                getDescricao().equals(produto.getDescricao()) &&
                getValor().equals(produto.getValor()) &&
                getFrete().equals(produto.getFrete()) &&
                getImagem().equals(produto.getImagem()) &&
                getVendido().equals(produto.getVendido()) &&
                getCmmComprador().equals(produto.getCmmComprador()) &&
                getNotaVenda().equals(produto.getNotaVenda());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getNickVendedor(), getNome(), getDescricao(), getValor(), getFrete(), getImagem(), getVendido(), getCmmComprador(), getNotaVenda());
    }
    
}
