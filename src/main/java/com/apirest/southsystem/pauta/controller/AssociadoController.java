package com.apirest.southsystem.pauta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.southsystem.pauta.document.Associado;
import com.apirest.southsystem.pauta.service.AssociadoService;

@RestController
@RequestMapping(value = "/associados")
public class AssociadoController {

	@Autowired
	private AssociadoService service;
	
	@GetMapping()
	public ResponseEntity<List<Associado>> findAll(){
		List<Associado> associados = service.findAll();
		return ResponseEntity.ok().body(associados);
	}
}
