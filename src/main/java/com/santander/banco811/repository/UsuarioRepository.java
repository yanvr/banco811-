package com.santander.banco811.repository;

import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Page<Usuario> findByNome(String nome, Pageable pageable);

    @Query("SELECT new com.santander.banco811.dto.UsuarioResponse(u.id, u.cpf, u.nome, u.dataCriacao, u.dataAtualizacao) from Usuario u where u.cpf = :cpf")
    Page<UsuarioResponse> findAllByCpf(@Param("cpf") String cpf, Pageable pageable);

//    List<Usuario> findByNomeAndCpf(String nome, String cpf);
//
//    List<Usuario> findByNomeIs(String nome);
//
//    List<Usuario> findByNomeIsNot(String nome);
//
//    List<Usuario> findByNomeIsNull();
//
//    List<Usuario> findByNomeIsNotNull();
//
//    List<Usuario> findByCpfStartingWith(String cpf);
//
//    List<Usuario> findByCpfEndingWith(String cpf);
//
//    List<Usuario> findByCpfContaining(String cpf);
//
//    List<Usuario> findByNomeLike(String pattern);
//
//    List<Usuario> findByDataCriacaoAfterAndNome(LocalDateTime dataCriacao, String nome);
//
//    List<Usuario> findByDataCriacao(LocalDateTime dataCriacao);
//
//    List<Usuario> findByNomeOrderBy(String nome);
//
//    Usuario findByCpf(String cpf);
}
