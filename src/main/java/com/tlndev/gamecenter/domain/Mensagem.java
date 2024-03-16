package com.tlndev.gamecenter.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Document(collection = "Mensagem")
public class Mensagem {

	@Id
    private ObjectId _id;

    private String topico;

    @NotNull
    @NotEmpty
    private String mensagem;

    @Reference
    @DBRef
    private Usuario usuario;

    public Mensagem() {
    	
    }
	
    public String get_id() {
        return _id.toHexString();
    }
}
