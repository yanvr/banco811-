package com.santander.banco811.dto;

import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.model.Conta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ContaResponse {

    private Integer id;
    private Integer numero;
    private Integer agencia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private BigDecimal saldo;
    private TipoConta tipoConta;

    public ContaResponse(Conta conta) {
        this.id = conta.getId();
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.dataCriacao = conta.getDataCriacao();
        this.dataAtualizacao = conta.getDataAtualizacao();
        this.saldo = conta.getSaldo();
        this.tipoConta = conta.getTipoConta();
    }
}
