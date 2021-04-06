package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.api.models.Ticket;

@EnableJpaRepositories
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
}
