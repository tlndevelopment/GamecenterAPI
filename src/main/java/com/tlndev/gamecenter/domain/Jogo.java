package com.tlndev.gamecenter.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Document(collection = "Jogo")
public class Jogo {

	@Id
    private ObjectId _id;

    private String titulo;
    private String foto;
    private String plataforma;
    private Double notaMidia;
    private Double notaUsuario;
    private Double notaUsuarios;
    private Double notaUsuariosTotal;
    private Double notas;

    public Jogo() {

    }
    
    public String get_id() {
        return _id.toHexString();
    }
	
}
