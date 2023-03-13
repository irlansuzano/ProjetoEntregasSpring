package com.irlan.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.irlan.algalog.api.dto.OcorrenciaDTO;
import com.irlan.algalog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component                  
public class OcorrenciaAssembler {

	private ModelMapper mm;
	
	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
		return mm.map(ocorrencia, OcorrenciaDTO.class);
	}
	
	public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias){
		 return ocorrencias.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
}
