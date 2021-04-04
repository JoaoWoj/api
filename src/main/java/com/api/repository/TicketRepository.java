package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.TicketModel;

public interface TicketRepository extends JpaRepository<TicketModel, Long> {

}
