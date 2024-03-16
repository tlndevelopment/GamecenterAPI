package com.tlndev.gamecenter.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Document(collection = "Topico")
public class Topico {
	
    @Id
    private ObjectId _id;

    private String comunidade;

    @NotNull
    @NotEmpty
    private String titulo;

    public Topico() {

    }
    
    public String get_id() {
        return _id.toHexString();
    }
	
}
