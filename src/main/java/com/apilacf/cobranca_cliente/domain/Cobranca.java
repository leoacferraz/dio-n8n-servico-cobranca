package com.apilacf.cobranca_cliente.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Cliente cliente;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusCobranca status;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    private LocalDate vencimento;

}
