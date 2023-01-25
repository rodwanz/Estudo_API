package com.wanzeler.estudoapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.wanzeler.estudoapi.domain.model.Entrega;
import com.wanzeler.estudoapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEnregaService {
	
	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void finalizar(Long entregId) {
		Entrega entrega = buscaEntregaService.buscar(entregId);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
		
	}

}
