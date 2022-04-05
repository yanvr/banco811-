package com.santander.banco811.controller;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @PostMapping(value = "/{id}")
    public ContaResponse create(@PathVariable("id") Integer id, ContaRequest contaRequest) {
        return contaService.create(id, contaRequest);
    }

    @GetMapping("/view")
    public List<ContaView> getAllContaViewByTipoConta(@RequestParam TipoConta tipoConta) {
        return contaService.getAllViewByTipoConta(tipoConta);
    }
}
