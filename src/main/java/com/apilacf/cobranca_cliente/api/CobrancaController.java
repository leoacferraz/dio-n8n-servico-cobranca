package com.apilacf.cobranca_cliente.api;

import com.apilacf.cobranca_cliente.api.dto.ListaCobrancasResponse;
import com.apilacf.cobranca_cliente.domain.Cobranca;
import com.apilacf.cobranca_cliente.domain.StatusCobranca;
import com.apilacf.cobranca_cliente.repository.CobrancaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cobrancas")
public class CobrancaController {

    private final CobrancaRepository repo;

    public CobrancaController(CobrancaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ListaCobrancasResponse listarCobrancas(
            @RequestParam(required = false) StatusCobranca status,
            @RequestParam(defaultValue = "50") int limit
    ) {
        if (limit <= 0 || limit > 1000) {
            limit = 50;
        }

        Pageable pageable = PageRequest.of(0, limit);

        List<Cobranca> cobrancas;

        if (status == null) {
            cobrancas = repo.findAll(pageable).getContent();
        }else{
            cobrancas = repo.findByStatus(status, pageable).getContent();
        }

        return new ListaCobrancasResponse(cobrancas);
    }
}
