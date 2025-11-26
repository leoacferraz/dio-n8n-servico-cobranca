package com.apilacf.cobranca_cliente.repository;

import com.apilacf.cobranca_cliente.domain.Cobranca;
import com.apilacf.cobranca_cliente.domain.StatusCobranca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CobrancaRepository extends JpaRepository<Cobranca, UUID> {
    Page<Cobranca> findByStatus(StatusCobranca status, Pageable pageable);
}
