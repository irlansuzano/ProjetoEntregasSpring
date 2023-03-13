package com.irlan.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irlan.algalog.domain.model.Cliente;

/**
 * 
 * @author Irlan
 *	Repositorio criado para gerenciar a entidade Cliente
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	List<Cliente> findByNome(String nome); //padrão base para criar o metodo usando o facilitador spring
	
	List<Cliente> findByNomeContaining(String nome); 
	
	Optional<Cliente> findByEmail(String email);
}
