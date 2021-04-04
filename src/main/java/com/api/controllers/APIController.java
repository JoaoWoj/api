package com.api.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.models.TicketModel;
import com.api.repository.TicketRepository;

@RestController
@RequestMapping("/api")
public class APIController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
// Teste para ver se chamadas da API estão funcionando	
	@GetMapping("/")
	public String testeChamadaAPI() {
		return "OK!";
	}
	
// Problema 01	
	@GetMapping("/listarTickets")
	public List<TicketModel> listarTickets() {
		return ticketRepository.findAll();
	}
	
	@PostMapping("/criarTicket")
	public String criarTicket() {
		return "Criar Ticket Ok!";
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
