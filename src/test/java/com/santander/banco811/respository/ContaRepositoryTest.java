package com.santander.banco811.respository;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.enums.TipoConta;
import com.santander.banco811.model.Conta;
import com.santander.banco811.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ContaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContaRepository contaRepository;

    @Test
    void validar_findAll_se_repository_estiver_vazio() {
        var contas = contaRepository.findAll();

        assertEquals(List.of(), contas);
    }

    @Test
    void validar_se_foi_encontrado_contas_findAll_com_sucesso() {
        Conta conta1 = new Conta(new ContaRequest(11111, 11111, new BigDecimal(2000), TipoConta.PF));
        Conta conta2 = new Conta(new ContaRequest(22222, 22222, new BigDecimal(4000), TipoConta.PJ));

        entityManager.persist(conta1);
        entityManager.persist(conta2);

        var contas = contaRepository.findAll();

        assertEquals(List.of(conta1, conta2), contas);
    }

    @Test
    void validar_se_contas_estao_sendo_salvas() {
        Conta conta = new Conta(new ContaRequest(11111, 11111, new BigDecimal(2000), TipoConta.PF));

        conta = entityManager.persist(conta);

        contaRepository.save(conta);

        var contaSalva = contaRepository.findById(conta.getId()).orElseThrow();

        assertEquals(conta.getNumero(), contaSalva.getNumero());
        assertEquals(conta.getAgencia(), contaSalva.getAgencia());
        assertEquals(conta.getSaldo(), contaSalva.getSaldo());
        assertEquals(conta.getTipoConta(), contaSalva.getTipoConta());
    }
}
