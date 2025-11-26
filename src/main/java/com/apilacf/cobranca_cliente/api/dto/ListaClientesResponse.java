package com.apilacf.cobranca_cliente.api.dto;

import com.apilacf.cobranca_cliente.domain.Cliente;

import java.util.List;

public record ListaClientesResponse(
        List<Cliente> clientes
) {
}
