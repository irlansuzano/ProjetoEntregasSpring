package com.irlan.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.irlan.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.irlan.algalog.domain.model.Entrega;
import com.irlan.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaEntregaService {

	private EntregaRepository er;
	
	public Entrega buscar(Long id) {
		return er.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada!"));
	}
}
