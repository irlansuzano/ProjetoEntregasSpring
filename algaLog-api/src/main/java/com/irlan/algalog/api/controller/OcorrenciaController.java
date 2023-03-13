package com.irlan.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irlan.algalog.api.assembler.OcorrenciaAssembler;
import com.irlan.algalog.api.dto.OcorrenciaDTO;
import com.irlan.algalog.api.dto.input.OcorrenciaInput;
import com.irlan.algalog.domain.model.Entrega;
import com.irlan.algalog.domain.model.Ocorrencia;
import com.irlan.algalog.domain.service.BuscaEntregaService;
import com.irlan.algalog.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private RegistroOcorrenciaService ros;
	private OcorrenciaAssembler oa;
	private BuscaEntregaService bes;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrencia = ros.registrar(entregaId, ocorrenciaInput.getDescricao());
		
		
		return oa.toDTO(ocorrencia);
	}
	
	public List<OcorrenciaDTO> listar(@PathVariable Long entregaId){
		Entrega entrega = bes.buscar(entregaId);
		
		return oa.toCollectionDTO(entrega.getOcorrencias());
	}
	
}
