package com.api.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.api.models.TicketModel;

@Repository
public class TicketCustomRepository {
	
	private final EntityManager em;
	
	public TicketCustomRepository(EntityManager em) {
		this.em = em;
	}
	
	public List<TicketModel> findByMesAno(Integer mes, Integer ano) {
		try {
			String query = "SELECT * "
					+ " FROM ticket "
					+ " WHERE EXTRACT(MONTH FROM data_abertura) = " + mes + " AND EXTRACT(YEAR FROM data_abertura) = " + ano;
			
			return em.createNativeQuery(query, TicketModel.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<TicketModel>();
		}
	}
}
