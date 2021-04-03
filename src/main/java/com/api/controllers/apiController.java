package com.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class apiController {
	
// Teste para ver se chamadas da API estão funcionando	
	@GetMapping("/")
	public String testeChamadaAPI() {
		return "OK!";
	}
	
// Problema 01	
	@GetMapping("/listarTickets")
	public String listarTickets() {
		return "Listar Tickets Ok!";
	}
	
	@PostMapping("/criarTicket")
	public String criarTicket() {
		return "Criar Ticket Ok!";
	}
	
// Problema 02
	@GetMapping("/receita")
	public String listarReceita() {
		return "Listar Receita Ok!";
	}

}
