package com.irlan.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irlan.algalog.api.assembler.EntregaAssembler;
import com.irlan.algalog.api.dto.EntregaDTO;
import com.irlan.algalog.api.dto.input.EntregaInput;
import com.irlan.algalog.domain.model.Entrega;
import com.irlan.algalog.domain.repository.EntregaRepository;
import com.irlan.algalog.domain.service.FinalizacaoEntregaService;
import com.irlan.algalog.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository er;
	private SolicitacaoEntregaService ses;
	private EntregaAssembler entregaAssembler; // dependencia que mapeia um objeto para outro tipo
	private FinalizacaoEntregaService fes;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput); //converte o recebido de DTO para entidade
		Entrega entregaSolicitada = ses.solicitar(novaEntrega);		   //realiza a solicitação no banco de dados	
		
		return entregaAssembler.toDto(entregaSolicitada);			   //retorna os dados para DTO
	}

	@GetMapping
	public List<EntregaDTO> listar() {
		
		return entregaAssembler.toCollectionDTO(er.findAll()); //passa a lista de entrega para ser transformada em dto 
	}

	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long id) {

		return er.findById(id).map(entrega -> ResponseEntity.ok(entregaAssembler.toDto(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable long entregaId) {
		fes.finalizar(entregaId);
	}

}
