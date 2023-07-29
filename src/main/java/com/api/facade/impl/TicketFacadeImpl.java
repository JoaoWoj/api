package com.api.facade.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.facade.TicketFacade;
import com.api.models.Ticket;
import com.api.models.TicketRetorno;
import com.api.repository.TicketCustomRepository;
import com.api.repository.TicketRepository;

public class TicketFacadeImpl implements TicketFacade {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketCustomRepository ticketCustomRepository;
	
	private Logger LOGGER = LoggerFactory.getLogger(TicketFacadeImpl.class);
	
	@Override
	public TicketRetorno listarTickets(Integer mes, Integer ano) {
		TicketRetorno retorno = new TicketRetorno();
		try {
			retorno.setTicketsRetorno(this.ticketCustomRepository.findByMesAno(mes, ano));
			
			Map<Object, Long> listaAgrupadaPorClienteCount = retorno.getTicketsRetorno().stream().collect(Collectors.groupingBy(t -> t.getCliente().getNome(), Collectors.counting()));
			retorno.setQuantidadePorCliente(listaAgrupadaPorClienteCount);
			
			Map<Object, List<Ticket>> listaAgrupadaPorCliente = retorno.getTicketsRetorno().stream().collect(Collectors.groupingBy(t -> t.getCliente().getId()));
			retorno.setTicketsPorCliente(listaAgrupadaPorCliente.values().stream().collect(Collectors.toList()));
			
			Map<Object, Long> listaAgrupadaPorModuloCount = retorno.getTicketsRetorno().stream().collect(Collectors.groupingBy(t -> t.getModulo().getNome(), Collectors.counting()));
			retorno.setQuantidadePorModulo(listaAgrupadaPorModuloCount);
			
			Map<Object, List<Ticket>> listaAgrupadaPorModulo = retorno.getTicketsRetorno().stream().collect(Collectors.groupingBy(t -> t.getModulo().getId()));
			retorno.setTicketsPorModulo(listaAgrupadaPorModulo.values().stream().collect(Collectors.toList()));
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao listar os tickets");
			return null;
		}
		return retorno;
	}

	@Override
	public String criarTicket(Ticket ticket) {
		try {
			if(ticket.getDataAbertura() == null) {
				ticket.setDataAbertura(new Date());
			}
			this.ticketRepository.save(ticket);
			LOGGER.error("Ticket " + ticket.getId() + " criado com sucesso!");
			return "Foi criado o ticket com o id: " + ticket.getId();
		} catch (Exception e) {
			LOGGER.error("Não foi possível criar ticket." + e.getMessage());
			return "";
		}
	}

}
