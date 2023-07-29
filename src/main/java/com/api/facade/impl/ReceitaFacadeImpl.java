package com.api.facade.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.facade.ReceitaFacade;

public class ReceitaFacadeImpl implements ReceitaFacade {
	
	private Logger LOGGER = LoggerFactory.getLogger(ReceitaFacadeImpl.class);

	@Override
	public String listarReceita(String nomePrato) {
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
			
            LOGGER.error("Listado com sucesso!");
			return new String(output.getBytes());
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro durante a execução: " + e.getMessage());
			return "";
		}
	}

}
