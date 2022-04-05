package com.santander.banco811.dto;

import lombok.Data;

@Data
public class UsuarioRequest {

    private String cpf;
    private String nome;
    private String senha;
}
