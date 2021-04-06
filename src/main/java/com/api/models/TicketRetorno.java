package com.api.models;

import java.util.List;
import java.util.Map;

public class TicketRetorno {
	
	private List<Ticket> ticketsRetorno;
	private Map<Object, Long> quantidadePorCliente;
	private List<List<Ticket>> ticketsPorCliente;
	private Map<Object, Long> quantidadePorModulo;
	private List<List<Ticket>> ticketsPorModulo;
	
	public List<Ticket> getTicketsRetorno() {
		return ticketsRetorno;
	}
	public void setTicketsRetorno(List<Ticket> ticketsRetorno) {
		this.ticketsRetorno = ticketsRetorno;
	}
	public List<List<Ticket>> getTicketsPorCliente() {
		return ticketsPorCliente;
	}
	public void setTicketsPorCliente(List<List<Ticket>> list) {
		this.ticketsPorCliente = list;
	}
	public List<List<Ticket>> getTicketsPorModulo() {
		return ticketsPorModulo;
	}
	public void setTicketsPorModulo(List<List<Ticket>> list) {
		this.ticketsPorModulo = list;
	}
	public Map<Object, Long> getQuantidadePorModulo() {
		return quantidadePorModulo;
	}
	public void setQuantidadePorModulo(Map<Object, Long> listaAgrupadaPorModuloCount) {
		this.quantidadePorModulo = listaAgrupadaPorModuloCount;
	}
	public Map<Object, Long> getQuantidadePorCliente() {
		return quantidadePorCliente;
	}
	public void setQuantidadePorCliente(Map<Object, Long> quantidadePorCliente) {
		this.quantidadePorCliente = quantidadePorCliente;
	}
}
