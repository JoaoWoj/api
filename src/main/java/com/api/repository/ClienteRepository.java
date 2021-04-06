package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
