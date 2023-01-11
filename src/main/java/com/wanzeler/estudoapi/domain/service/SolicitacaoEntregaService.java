package com.wanzeler.estudoapi.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.wanzeler.estudoapi.domain.exception.NegocioException;
import com.wanzeler.estudoapi.domain.model.Cliente;
import com.wanzeler.estudoapi.domain.model.Entrega;
import com.wanzeler.estudoapi.domain.model.StatusEntrega;
import com.wanzeler.estudoapi.domain.repository.ClienteRepository;
import com.wanzeler.estudoapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SolicitacaoEntregaService {
	
	private CatalogoClienteService catalogoClienteService;
	private EntregaRepository entregarepository;
	
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PEDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		return entregarepository.save(entrega);
	}
}
