package com.irlan.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.irlan.algalog.domain.model.Entrega;
import com.irlan.algalog.domain.model.StatusEntrega;
import com.irlan.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private BuscaEntregaService bes;
	private EntregaRepository er;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = bes.buscar(entregaId);
		
		entrega.finalizar();
		er.save(entrega);		
	}
	
}
