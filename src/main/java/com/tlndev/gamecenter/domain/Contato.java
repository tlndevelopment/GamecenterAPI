package com.tlndev.gamecenter.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Contato {

    private String estado;
    private String cidade;
    private String CEP;
    private String rua;
    private String numero;
    private String telefone;

    public Contato() {

    }
}
