package com.tlndev.gamecenter.domain;

import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Document(collection = "Usuario")
public class Usuario {
    
	@Id
    private ObjectId _id;

    @NotNull
    @NotEmpty
    @Indexed(unique = true)
    private String nick;

    @NotNull
    @NotEmpty
    @Email
    @Indexed(unique = true)
    private String email;

    @NotNull
    @NotEmpty
    private String senha;

    private Contato contato;

    private String nomeReal;

    private String foto;

    private Boolean vendedor;

    private List<Jogo> jogos;

    public Usuario() {

    }
    
    public String get_id() {
        return _id.toHexString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return get_id().equals(usuario.get_id()) &&
                getNick().equals(usuario.getNick()) &&
                getEmail().equals(usuario.getEmail()) &&
                getSenha().equals(usuario.getSenha()) &&
                getContato().equals(usuario.getContato()) &&
                getNomeReal().equals(usuario.getNomeReal()) &&
                getFoto().equals(usuario.getFoto()) &&
                getVendedor().equals(usuario.getVendedor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getNick(), getEmail(), getSenha(), getContato(), getNomeReal(), getFoto(), getVendedor());
    }
    
}
