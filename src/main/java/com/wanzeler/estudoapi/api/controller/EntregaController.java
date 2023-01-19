package com.wanzeler.estudoapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wanzeler.estudoapi.api.model.DestinatarioModel;
import com.wanzeler.estudoapi.api.model.EntregaModel;
import com.wanzeler.estudoapi.domain.model.Entrega;
import com.wanzeler.estudoapi.domain.repository.EntregaRepository;
import com.wanzeler.estudoapi.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
		return solicitacaoEntregaService.solicitar(entrega);
	}
	
	@GetMapping
	public List<Entrega> listar(){
		return entregaRepository.findAll();
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> bscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> {
					EntregaModel entregaModel = new EntregaModel();
					entregaModel.setId(entrega.getId());
					entregaModel.setNomeCliente(entrega.getCliente().getNome());
					entregaModel.setDestinatario(new DestinatarioModel());
					entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
					entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
					entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
					entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
					entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
					entregaModel.setTaxa(entrega.getTaxa());
					entregaModel.setDataPedido(entrega.getDataPedido());
					entregaModel.setDataFinalizado(entrega.getDataFinalizado());
					return ResponseEntity.ok(entregaModel);
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
