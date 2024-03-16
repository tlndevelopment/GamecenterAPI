package com.tlndev.gamecenter.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Document(collection = "Comunidade")
public class Comunidade {

    @Id
    private ObjectId _id;

    @NotNull
    @NotEmpty
    @Indexed(unique=true)
    private String nome;
    
    private String descricao;
    private String imagem;

    public Comunidade() {

    }
    
    public String get_id() {
        return _id.toHexString();
    }
	
}
