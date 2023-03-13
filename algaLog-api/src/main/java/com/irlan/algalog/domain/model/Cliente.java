package com.irlan.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.irlan.algalog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity 												 // funciona como o table, só que procura a tabela pelo nome da classe
public class Cliente {
	
	
//	@NotNull(groups = ValidationGroups.ClienteId.class) 					
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //estrategia de geração da chave (IDENTITY é a forma nativa do banco de dados)
	private Long id;
	
	@NotBlank											 //não permite que o campo chegue nulo ou vazio
	@Size(max = 60)										 //define o tamanho do campo que chega
	@Column(name = "nome")								 // se for o mesmo nome da variavel, n precisa da column
	private String nome;
	
	@NotBlank
	@Email												//informa que esse campo tem que ter corpo de email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	private String telefone;		
		
		
}
