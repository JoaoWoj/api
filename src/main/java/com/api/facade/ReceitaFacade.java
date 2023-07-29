package com.api.facade;

import org.springframework.stereotype.Service;

@Service
public interface ReceitaFacade {
	
	public String listarReceita(String nomePrato);

}
