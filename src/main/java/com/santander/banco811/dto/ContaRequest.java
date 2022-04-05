package com.santander.banco811.dto;

import com.santander.banco811.enums.TipoConta;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaRequest {

    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;
    private TipoConta tipoConta;
}
