package com.irlan.algalog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean									//inicializa e configura um bean para ser usado e identificado pelo spring *SERVE PARA INJEÇÃO EM OUTRAS CLASSES*
	public ModelMapper modelMapper() {		
		return new ModelMapper();			
		
	}
	
}
