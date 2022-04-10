package com.santander.banco811.controller;

import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    final UsuarioService usuarioService;

    @GetMapping
    public Page<Usuario> getAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size
    ) {
        return usuarioService.getAll(nome, page, size);
    }

    @GetMapping("/search")
    public List<Usuario> search(@RequestParam String search) {
        return usuarioService.search(search);
    }

    @GetMapping("/cpf")
    public Page<UsuarioResponse> getAllByCpf(
            @RequestParam String cpf,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size
    ) {
        return usuarioService.getAllByCpf(cpf, page, size);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioResponse create(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.create(usuarioRequest);
    }

    @GetMapping(value = "/{id}")
    public UsuarioResponse getById(@PathVariable Integer id) {
        return usuarioService.getById(id);
    }

    @PatchMapping(value = "/{id}")
    public UsuarioResponse update(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.update(usuarioRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }
}
