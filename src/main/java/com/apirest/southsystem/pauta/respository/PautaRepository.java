package com.apirest.southsystem.pauta.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.apirest.southsystem.pauta.document.Pauta;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, String> {
	
}
