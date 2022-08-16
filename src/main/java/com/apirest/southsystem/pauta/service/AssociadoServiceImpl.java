package com.apirest.southsystem.pauta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.southsystem.pauta.document.Associado;
import com.apirest.southsystem.pauta.exceptions.EntityNotFoundException;
import com.apirest.southsystem.pauta.respository.AssociadoRepository;

@Service
public class AssociadoServiceImpl implements AssociadoService {

	@Autowired
	private AssociadoRepository repository;
	
	@Override
	public List<Associado> findAll() {		
		return repository.findAll();
	}

	@Override
	public Associado findById(String id) {		
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}
	
	
}