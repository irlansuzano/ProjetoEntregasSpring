package com.irlan.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.irlan.algalog.domain.exception.NegocioException;
import com.irlan.algalog.domain.model.Cliente;
import com.irlan.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

/**
 * 
 * @author Irlan
 * Classe de regras de negocio da classe de cliente
 *
 */

@AllArgsConstructor							//pode ser substituido pelo autoWired
@Service									//componente spring que representa os serviços que serão executados (a camada de negocio)
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;		
	
	public Cliente buscar(Long clienteId) {
	Cliente cliente =clienteRepository.findById(clienteId)
			.orElseThrow(() -> new NegocioException("CLIENTE NÃO ENCONTRADO"));
	
	return cliente;
	}
	
	@Transactional							//declara que o metodo deve ser executado dentro de uma transação (se der erro em algo, a alteração é descartada)
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream().anyMatch(c -> !c.equals(cliente));		//busca os dados pelo email, e verifica se os dados passados já existem no banco
		
		if(emailEmUso) {
			throw new NegocioException("Email já utilizado em outro cliente");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
	
	
}
