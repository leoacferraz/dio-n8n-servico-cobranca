package com.apilacf.cobranca_cliente.repository;

import com.apilacf.cobranca_cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
