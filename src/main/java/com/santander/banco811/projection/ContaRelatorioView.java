package com.santander.banco811.projection;

import com.santander.banco811.enums.TipoConta;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigInteger;

public interface ContaRelatorioView {

    @Value("#{target.agencia + ' - ' + target.numero}")
    String getAgenciaNumero();

    TipoConta getTipoConta();

    BigInteger getSaldo();

    UsuarioView getUsuario();
}
