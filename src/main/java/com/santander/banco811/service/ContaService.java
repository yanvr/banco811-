package com.santander.banco811.service;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.projection.ContaView;

import java.util.List;

public interface ContaService {

    ContaResponse create(Integer usuarioId, ContaRequest contaRequest);

    List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);
}
