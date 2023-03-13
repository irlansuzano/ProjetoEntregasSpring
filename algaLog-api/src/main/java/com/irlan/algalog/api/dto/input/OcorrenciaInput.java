package com.irlan.algalog.api.dto.input;

import lombok.Setter;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
@Setter
public class OcorrenciaInput {

	@NotBlank
	private String descricao;
}
