package com.santander.banco811.dto;

import com.santander.banco811.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ContaRequest {

    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;
    private TipoConta tipoConta;
}
