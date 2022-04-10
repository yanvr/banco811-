package com.santander.banco811.model;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.enums.TipoConta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "conta")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "agencia")
    private Integer agencia;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "tipo_conta")
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "conta")
    private List<Transacao> transacoes;

    public static Conta of(ContaRequest contaRequest) {
        Conta conta = new Conta();
        BeanUtils.copyProperties(contaRequest, conta);
        return conta;
    }

    public Conta(ContaRequest contaRequest) {
        this.numero = contaRequest.getNumero();
        this.agencia = contaRequest.getAgencia();
        this.saldo = contaRequest.getSaldo();
        this.tipoConta = contaRequest.getTipoConta();
    }
}
