package com.apirest.southsystem.pauta.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.southsystem.pauta.document.Pauta;
import com.apirest.southsystem.pauta.service.PautaService;

@RestController
@RequestMapping(value = "/pautas")
public class PautaController {
	
	@Autowired
	private PautaService service;
	
	@GetMapping()
	public ResponseEntity<List<Pauta>> findAll(){
		List<Pauta> pautas = service.findAll();
		return ResponseEntity.ok().body(pautas);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pauta> findById(@PathVariable String id){
		Pauta pauta = service.findById(id);
		return ResponseEntity.ok().body(pauta);
	}

	@PostMapping(value = "cadastrar-nova-pauta")
	public ResponseEntity<Pauta> cadastrarNovaPauta(@Valid @RequestBody Pauta pauta) {		
		return ResponseEntity.ok().body(service.cadastrarNovaPauta(pauta)); 
	}
	
	@PostMapping(value = "abrir-secao-votacao/{id}")
	public ResponseEntity<Pauta> abrirSecaoVotacao(@PathVariable String id, @RequestParam Integer minutosEmAberto) {		
		return ResponseEntity.ok().body(service.abrirSecaoVotacao(id, minutosEmAberto)); 
	}
	
	@PostMapping(value = "receber-voto-associado/{id}")
	public ResponseEntity<Pauta> receberVotoAssociado(@PathVariable String id
			, @RequestParam 
				@NotBlank(message = "idAssociado não poder espaço em branco") 
				@NotNull(message = "idAssociado não pode ser nulo") 
				@NotEmpty (message = "idAssociado não pode ser vazio") String idAssociado
			, @RequestParam 
				@NotBlank(message = "voto não poder espaço em branco") 
				@NotNull(message = "voto não pode ser nulo") 
				@NotEmpty (message = "voto não pode ser vazio") String voto) {		
		return ResponseEntity.ok().body(service.receberVotoAssociado(id, idAssociado, voto)); 
	}
	
	@GetMapping(value = "emitir-resultado-votacao/{id}")
	public ResponseEntity<Pauta> emitirResultadoVotacao(@PathVariable String id){
		Pauta pauta = service.emitirResultadoVotacao(id);
		return ResponseEntity.ok().body(pauta);
	}
}
