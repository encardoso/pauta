package com.apirest.southsystem.pauta.service;

import java.util.List;

import com.apirest.southsystem.pauta.document.Associado;

public interface AssociadoService {
	
	List<Associado> findAll();
	
	Associado findById(String id);

}
