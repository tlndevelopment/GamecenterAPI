package com.tlndev.gamecenter.domain;

import java.util.Objects;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Document(collection = "Vendedor")
public class Vendedor {

    @Id
    private ObjectId _id;

    @NotNull
    @NotEmpty
    @Indexed(unique=true)
    @CPF
    private String cpf;

    private Double notaVendedor;
    private Double notaTotal;
    private Double vendas;

    private Usuario usuario;

    public Vendedor() {
    	
    }
    
    public String get_id() {
        return _id.toHexString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendedor)) return false;
        Vendedor vendedor = (Vendedor) o;
        return get_id().equals(vendedor.get_id()) &&
                getCpf().equals(vendedor.getCpf()) &&
                getNotaVendedor().equals(vendedor.getNotaVendedor()) &&
                getUsuario().equals(vendedor.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getCpf(), getNotaVendedor(), getUsuario());
    }
	
}
