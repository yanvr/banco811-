package com.santander.banco811.respository;

import com.santander.banco811.model.Usuario;
import com.santander.banco811.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void validar_findAll_vazio_se_repository_em_branco() {
        var usuarios = usuarioRepository.findAll();

        assertEquals(List.of(), usuarios);
    }

    @Test
    void trazer_trazer_usuarios_cadastrados_find_all() {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Maria");
        usuario1.setSenha("1234");
        usuario1.setCpf("1111111111111");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Pedro");
        usuario2.setSenha("1234");
        usuario2.setCpf("22222222222222");

        entityManager.persist(usuario1);
        entityManager.persist(usuario2);

        var usuarios = usuarioRepository.findAll();

        assertEquals(List.of(usuario1, usuario2), usuarios);
    }

    @Test
    void trazer_usuarios_pelo_nome_com_sucesso() {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Maria");
        usuario1.setSenha("1234");
        usuario1.setCpf("1111111111111");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Pedro");
        usuario2.setSenha("1234");
        usuario2.setCpf("22222222222222");

        entityManager.persist(usuario1);
        entityManager.persist(usuario2);

        PageRequest pageRequest = PageRequest.of(0, 3);

        var usuarios = usuarioRepository.findByNome("Maria", pageRequest);

        assertEquals(1, usuarios.getTotalElements());
        assertEquals(usuario1, usuarios.stream().findFirst().get());
    }

    @Test
    void editar_usuario_com_sucesso() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha("1234");
        usuario.setCpf("1111111111111");

      usuario = entityManager.persist(usuario);

      usuario.setNome("Teste");

      usuarioRepository.save(usuario);

      var usuarioEncontrado = usuarioRepository.findById(usuario.getId()).get();

      assertEquals(usuarioEncontrado.getId(), usuario.getId());
      assertEquals("Teste", usuario.getNome());

    }

}
