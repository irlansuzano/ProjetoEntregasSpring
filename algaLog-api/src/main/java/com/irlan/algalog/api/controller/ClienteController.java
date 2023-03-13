package com.irlan.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irlan.algalog.domain.model.Cliente;
import com.irlan.algalog.domain.repository.ClienteRepository;
import com.irlan.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;


@AllArgsConstructor													// outra forma de injetar um instancia 
@RestController
@RequestMapping("/clientes")										//se vc passa o requestMapping, não é necessario escrever o caminho base da classe a cada endpoint
public class ClienteController {

//	@PersistenceContext												//injeta um manager nessa variavel de instancia
//	private EntityManager manager;									//interface do persistence para fazer as operações com as entidades (incluir, etc)
	
	@Autowired														//define que queremos injetar uma instancia gerenciada pelo spring
	private ClienteRepository clienteRepository;
	
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping 													//nomenclatura usada para apontar o path da requisição HTML
	public List<Cliente> listar() {
	return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar (@PathVariable Long id) { //response entity serve para retornar não só o pedido, como tambem o  status da busca (OK, NOT FOUND, etc)
		return  clienteRepository.findById(id)
					.map(ResponseEntity::ok)						//vai mapear a findById, se buscar alguma coisa (que não seja nulo), retorna status ok
					.orElse(ResponseEntity.notFound().build());		// se não, retorna not found

	}
	
	@ResponseStatus(HttpStatus.CREATED)								//passa o status criado para a função post
	@PostMapping
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
//		return clienteRepository.save(cliente);
		return catalogoClienteService.salvar(cliente);
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente){	//@Valid já aponta o erro nos parametros antes de enviar a requisição 
																											
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id); 											//JPA identifica que o cliente ja existe a partir de vc passar o id repetido, atualizando os dados
		
//		cliente = clienteRepository.save(cliente);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
//		clienteRepository.deleteById(id);
		catalogoClienteService.excluir(id);
	
		return ResponseEntity.noContent().build();					//retorna codigo 204 (sem body na resposta)
		
	}
	
	
	
}
