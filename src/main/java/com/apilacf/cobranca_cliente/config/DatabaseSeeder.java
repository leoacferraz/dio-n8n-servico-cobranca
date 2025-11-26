package com.apilacf.cobranca_cliente.config;

import com.apilacf.cobranca_cliente.domain.Cliente;
import com.apilacf.cobranca_cliente.domain.Cobranca;
import com.apilacf.cobranca_cliente.domain.StatusCobranca;
import com.apilacf.cobranca_cliente.domain.TipoPagamento;
import com.apilacf.cobranca_cliente.repository.ClienteRepository;
import com.apilacf.cobranca_cliente.repository.CobrancaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final CobrancaRepository cobrancaRepository;
    private final Random random = new Random();

    public DatabaseSeeder(ClienteRepository clienteRepository,
                          CobrancaRepository cobrancaRepository) {
        this.clienteRepository = clienteRepository;
        this.cobrancaRepository = cobrancaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (clienteRepository.count() > 0 || cobrancaRepository.count() > 0) {
            return;
        }

        for (int i = 1; i <= 150; i++) {
            Cliente cliente = Cliente.builder()
                    .nome("Cliente " + i)
                    .email("cliente" + i + "@teste.com")
                    .build();

            Cliente salvo = clienteRepository.save(cliente);

            Cobranca cobranca = Cobranca.builder()
                    .cliente(salvo)
                    .valor(BigDecimal.valueOf(50 + random.nextInt(450))) // 50 a 499
                    .status(randomStatus())
                    .tipoPagamento(randomTipoPagamento())
                    .vencimento(LocalDate.now().plusDays(random.nextInt(30))) // próximos 30 dias
                    .build();

            cobrancaRepository.save(cobranca);
        }

    }

    private StatusCobranca randomStatus() {
        StatusCobranca[] values = StatusCobranca.values();
        return values[random.nextInt(values.length)];
    }
    private TipoPagamento randomTipoPagamento() {
        TipoPagamento[] values = TipoPagamento.values();
        return values[random.nextInt(values.length)];
    }

}

