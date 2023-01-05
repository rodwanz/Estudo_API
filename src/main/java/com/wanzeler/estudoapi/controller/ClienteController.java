package com.wanzeler.estudoapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wanzeler.estudoapi.domain.model.Cliente;
import com.wanzeler.estudoapi.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ClienteController {


	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/nome")
	public List<Cliente> listarNome() {
		return clienteRepository.findByNome("Agostinho Hipona");
	}
	
	@GetMapping("/clientes/nomequalquer")
	public List<Cliente> listarNomeSemExatidao() {
		return clienteRepository.findByNomeContaining("i");
	}
	
	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
}
