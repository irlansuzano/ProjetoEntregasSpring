package com.irlan.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.irlan.algalog.domain.model.Entrega;
import com.irlan.algalog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	private BuscaEntregaService bes;
	
	@Transactional
	public Ocorrencia registrar(Long id, String descricao) {
		Entrega entrega = bes.buscar(id);
		
		return entrega.addOcorrencia(descricao);
		
	}
	
}
