package com.apilacf.cobranca_cliente.api;

import com.apilacf.cobranca_cliente.api.dto.ListaClientesResponse;
import com.apilacf.cobranca_cliente.domain.Cliente;
import com.apilacf.cobranca_cliente.repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repo;

    public ClienteController(ClienteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ListaClientesResponse listarClientes(@RequestParam(defaultValue = "50") int limit) {
        if (limit <= 0 || limit > 1000) {
            limit = 50;
        }

        Pageable pageable = PageRequest.of(0, limit);
        List<Cliente> clientes = repo.findAll(pageable).getContent();

        return new ListaClientesResponse(clientes);
    }
}
