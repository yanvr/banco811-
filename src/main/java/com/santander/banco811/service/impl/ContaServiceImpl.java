package com.santander.banco811.service.impl;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.repository.ContaRepository;
import com.santander.banco811.service.ContaService;
import com.santander.banco811.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public ContaResponse create(Integer usuarioId, ContaRequest contaRequest) {
        var usuario = Usuario.toModel(usuarioService.getById(usuarioId));
        Conta conta = Conta.of(contaRequest);
        conta.setUsuario(usuario);
        Conta contaSalva = contaRepository.save(conta);
        return ContaResponse.of(contaSalva);
    }

    @Override
    public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
        return contaRepository.findAllByTipoConta(tipoConta);
    }
}
