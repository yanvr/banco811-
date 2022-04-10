package com.santander.banco811.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    public String campo;
    public String mensagemErro;
    public LocalDateTime horario;
}
