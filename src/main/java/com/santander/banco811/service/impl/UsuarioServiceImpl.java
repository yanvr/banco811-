package com.santander.banco811.service.impl;

import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.repository.UsuarioRepository;
import com.santander.banco811.service.UsuarioService;
import com.santander.banco811.specification.UsuarioSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Page<Usuario> getAll(String nome, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        if (nome != null) {
            return usuarioRepository.findByNome(nome, pageRequest);

        } else {
            return usuarioRepository.findAll(pageRequest);
        }
    }

    @Override
    public List<Usuario> search(String search) {
        UsuarioSpecificationBuilder builder = new UsuarioSpecificationBuilder();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while(matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Usuario> specs = builder.build();

        return usuarioRepository.findAll(specs);
    }

    @Override
    public Page<UsuarioResponse> getAllByCpf(String cpf, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, page, Sort.Direction.ASC, "nome");
        return usuarioRepository.findAllByCpf(cpf, pageRequest);
    }

    @Override
    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario(usuarioRequest);
        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    @Override
    public Usuario getByIdModel(Integer id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    @Override
    public UsuarioResponse getById(Integer id) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));

        return new UsuarioResponse(usuario);
    }

    @Override
    public UsuarioResponse update(UsuarioRequest usuarioRequest, Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(usuarioRequest, usuario, "id");
        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    @Override
    public void delete(Integer id) {
        var usuario = usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.delete(usuario);
    }
}
