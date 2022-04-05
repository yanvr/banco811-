package com.santander.banco811.projection;

import com.santander.banco811.enums.TipoConta;
import org.springframework.beans.factory.annotation.Value;

public interface ContaView {

    Integer getSaldo();

    TipoConta getTipoConta();

    @Value("#{target.numero + ' - ' + target.agencia}")
    String getNumeroAgencia();

    UsuarioView getUsuarioView();
}
