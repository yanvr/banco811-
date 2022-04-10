package com.santander.banco811.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioRequest {

    @NotNull
    @NotEmpty
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 10)
    private String senha;
}
