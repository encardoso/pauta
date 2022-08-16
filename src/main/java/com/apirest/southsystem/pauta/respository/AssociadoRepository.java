package com.apirest.southsystem.pauta.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apirest.southsystem.pauta.document.Associado;

public interface AssociadoRepository extends MongoRepository<Associado, String> {

}
