package com.wanzeler.estudoapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.wanzeler.estudoapi.domain.model.Cliente;
import com.wanzeler.estudoapi.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {


	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/nome")
	public List<Cliente> listarNome() {
		return clienteRepository.findByNome("Agostinho Hipona");
	}
	
	@GetMapping("/nomequalquer")
	public List<Cliente> listarNomeSemExatidao() {
		return clienteRepository.findByNomeContaining("i");
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicinar(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizando(@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Cliente> excluindo(@PathVariable Long clienteId){
			if(!clienteRepository.existsById(clienteId)) {
				return ResponseEntity.notFound().build();
			}
			clienteRepository.deleteById(clienteId);
			return ResponseEntity.notFound().build();
	}
}
