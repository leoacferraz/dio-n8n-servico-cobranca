package com.apilacf.cobranca_cliente.api.dto;

import com.apilacf.cobranca_cliente.domain.Cobranca;

import java.util.List;

public record ListaCobrancasResponse(
        List<Cobranca> cobrancas
) {
}
