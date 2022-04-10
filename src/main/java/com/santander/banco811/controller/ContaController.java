package com.santander.banco811.controller;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.model.Conta;
import com.santander.banco811.projection.ContaRelatorioView;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{id}")
    public ContaResponse create(@PathVariable("id") Integer id, @RequestBody ContaRequest contaRequest) {
        return contaService.create(id, contaRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<Conta> getAllPageable(@RequestParam int page, @RequestParam int size) {
        return contaService.getAllPageable(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/view")
    public List<ContaView> getAllContaViewByTipoConta(@RequestParam TipoConta tipoConta) {
        return contaService.getAllViewByTipoConta(tipoConta);
    }

    @GetMapping("/")
    public List<ContaResponse> getallContaByDataCriacao(@RequestParam LocalDateTime dataCriacao) {
        return contaService.getAllContaByDataCriacao(dataCriacao);
    }

    @GetMapping("/relatorio-view")
    public List<ContaRelatorioView> getAllContaByUsuarioId(@RequestParam Integer id) {
        return contaService.getAllByUsuarioId(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ContaResponse update(@PathVariable Integer id, @RequestBody ContaRequest contaRequest) {
        return contaService.update(id, contaRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        contaService.delete(id);
    }
}
