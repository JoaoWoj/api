package com.api.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.models.Ticket;
import com.api.models.TicketRetorno;
import com.api.repository.TicketCustomRepository;
import com.api.repository.TicketRepository;

@RestController
@RequestMapping("/api")
public class APIController {
	
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private TicketCustomRepository ticketCustomRepository;
	
// Teste para ver se chamadas da API estão funcionando	
	@GetMapping("/")
	public String testeRequisicaoAPI() {
		return "OK!";
	}
	
// Problema 01	
	@GetMapping("/listarTickets/mes={mes}&ano={ano}")
	public TicketRetorno listarTickets(@PathVariable Integer mes, @PathVariable Integer ano) {
		try {
			TicketRetorno retorno = new TicketRetorno();
			retorno.setTicketsRetorno(this.ticketCustomRepository.findByMesAno(mes, ano));
			
			// Count por cliente
			Map<Object, Long> listaAgrupadaPorClienteCount = retorno.getTicketsRetorno().stream().collect(Collectors.groupingBy(t -> t.getCliente().getNome(), Collectors.counting()));
			retorno.setQuantidadePorCliente(listaAgrupadaPorClienteCount);
			
			//Agrupa por cliente
			Map<Object, List<Ticket>> listaAgrupadaPorCliente = retorno.getTicketsRetorno().stream().collect(Collectors.groupingBy(t -> t.getCliente().getId()));
			retorno.setTicketsPorCliente(listaAgrupadaPorCliente.values().stream().collect(Collectors.toList()));
			
			// Count por módulo
			Map<Object, Long> listaAgrupadaPorModuloCount = retorno.getTicketsRetorno().stream().collect(Collectors.groupingBy(t -> t.getModulo().getNome(), Collectors.counting()));
			retorno.setQuantidadePorModulo(listaAgrupadaPorModuloCount);
			
			//Agrupa por módulo
			Map<Object, List<Ticket>> listaAgrupadaPorModulo = retorno.getTicketsRetorno().stream().collect(Collectors.groupingBy(t -> t.getModulo().getId()));
			retorno.setTicketsPorModulo(listaAgrupadaPorModulo.values().stream().collect(Collectors.toList()));
			
			return retorno;
		} catch (Exception e) {
			return new TicketRetorno();
		}
	}
	
	@PostMapping("/criarTicket")
	public String criarTicket(@RequestBody Ticket ticket) {
		try {
			if(ticket.getDataAbertura() == null) {
				ticket.setDataAbertura(new Date());
			}
			this.ticketRepository.save(ticket);
			return "Foi criado o ticket com o id: " + ticket.getId();
		} catch (Exception e) {
			return "Não foi possível criar o ticket!";
		}
	}
	
// Problema 02
	@GetMapping("/receita/prato={nomePrato}")
	public String listarReceita(@PathVariable String nomePrato) {
		try {
			String url = "https://forkify-api.herokuapp.com/api/search?q=" + nomePrato;
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				return "Prato não encontrado!";
			 }
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output += line;
            }

            conn.disconnect();
			
			return new String(output.getBytes());
		} catch (Exception e) {
			return "Ocorreu um erro durante a execução: " + e.getMessage();
		}
	}
}
