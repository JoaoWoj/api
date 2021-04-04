package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
