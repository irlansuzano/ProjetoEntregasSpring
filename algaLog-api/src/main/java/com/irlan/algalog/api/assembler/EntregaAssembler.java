package com.irlan.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.irlan.algalog.api.dto.EntregaDTO;
import com.irlan.algalog.api.dto.input.EntregaInput;
import com.irlan.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

/*
 * 								(*ASSEMBLER*)
 * Classe responsável por fazer a montagem de objetos para outros tipos.
 * 
 * 
 * 	é necessário que seja criada uma uma classe com um bean de ModelMapper para que o spring identifique (ModelMapperConfig)
 *	transforma todos os dados para a classe apontada no segundo argumento
 * 
 * 
 */
@AllArgsConstructor
@Component
public class EntregaAssembler {

	private ModelMapper modelMapper;

	public EntregaDTO toDto(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}
	
	public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas){
		return entregas.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
