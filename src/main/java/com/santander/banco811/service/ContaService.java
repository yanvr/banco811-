package com.santander.banco811.service;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.model.Conta;
import com.santander.banco811.projection.ContaRelatorioView;
import com.santander.banco811.projection.ContaView;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface ContaService {

    ContaResponse create(Integer usuarioId, ContaRequest contaRequest);

    List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);

    Page<Conta> getAllPageable(int page, int size);

    ContaResponse update(Integer id, ContaRequest contaRequest);

    void delete(Integer id);

    List<ContaRelatorioView> getAllByUsuarioId(Integer id);

    List<ContaResponse> getAllContaByDataCriacao(LocalDateTime dataCriacao);
}
