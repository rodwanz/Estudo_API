package com.wanzeler.estudoapi.domain.service;

import org.springframework.stereotype.Service;

import com.wanzeler.estudoapi.domain.exception.EntidadeNaoEncontradaException;
import com.wanzeler.estudoapi.domain.model.Entrega;
import com.wanzeler.estudoapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> 
				new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
}
