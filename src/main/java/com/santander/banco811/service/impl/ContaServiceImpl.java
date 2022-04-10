package com.santander.banco811.service.impl;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.model.Conta;
import com.santander.banco811.projection.ContaRelatorioView;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.repository.ContaRepository;
import com.santander.banco811.service.ContaService;
import com.santander.banco811.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        var usuario = usuarioService.getByIdModel(usuarioId);
        Conta conta = Conta.of(contaRequest);
        conta.setUsuario(usuario);
        Conta contaSalva = contaRepository.save(conta);
        return new ContaResponse(contaSalva);
    }

    @Override
    public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
        return contaRepository.findAllByTipoConta(tipoConta);
    }

    @Override
    public Page<Conta> getAllPageable(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        return contaRepository.findAll(pageRequest);
    }

    @Override
    public ContaResponse update(Integer id, ContaRequest contaRequest) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(contaRequest, conta, "id");
        Conta contaAtualizada = contaRepository.save(conta);
        return new ContaResponse(contaAtualizada);
    }

    @Override
    public void delete(Integer id) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        contaRepository.delete(conta);
    }

    @Override
    public List<ContaRelatorioView> getAllByUsuarioId(Integer id) {
        return contaRepository.findAllByUsuarioId(id);
    }

    @Override
    public List<ContaResponse> getAllContaByDataCriacao(LocalDateTime dataCriacao) {
        return contaRepository.findContasByDataCriacao(dataCriacao);
    }
}
