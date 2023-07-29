package com.api.facade;

import org.springframework.stereotype.Service;

import com.api.models.Ticket;
import com.api.models.TicketRetorno;

@Service
public interface TicketFacade {
	
	public TicketRetorno listarTickets(Integer mes, Integer ano);
	
	public String criarTicket(Ticket ticket);

}
