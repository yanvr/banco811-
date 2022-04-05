package com.santander.banco811.dto;

import com.santander.banco811.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UsuarioResponse {

    private Integer id;
    private String nome;
    private String cpf;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.dataCriacao = usuario.getDataCriacao();
        this.dataAtualizacao = usuario.getDataAtualizacao();
    }

    public static List<UsuarioResponse> toResponse(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }
}
