package com.wanzeler.estudoapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanzeler.estudoapi.domain.model.Cliente;

@RestController
//@RequestMapping(value = "/api")
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1l);
		cliente1.setNome("Antonio Nunes Oliveira");
		cliente1.setEmail("nuno@gmail.com");
		cliente1.setTelefone("21 - 99907-5678");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2l);
		cliente2.setNome("Roberio Pereira Penedo");
		cliente2.setEmail("roper@gmail.com");
		cliente2.setTelefone("21 - 99566-5678");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
