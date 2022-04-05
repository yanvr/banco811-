package com.santander.banco811.dto;

import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.model.Conta;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ContaResponse {

    private Integer numero;
    private Integer agencia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private BigDecimal saldo;
    private TipoConta tipoConta;

    public static ContaResponse of(Conta contaSalva) {
        ContaResponse contaResponse = new ContaResponse();
        BeanUtils.copyProperties(contaSalva, contaResponse);
        return contaResponse;
    }
}
