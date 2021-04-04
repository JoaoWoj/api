package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.ModuloModel;

public interface ModuloRepository extends JpaRepository<ModuloModel, Long> {

}
