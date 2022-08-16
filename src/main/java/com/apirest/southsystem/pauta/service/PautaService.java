package com.apirest.southsystem.pauta.service;

import java.util.List;

import com.apirest.southsystem.pauta.document.Pauta;
import com.apirest.southsystem.pauta.exceptions.SistemaException;

public interface PautaService {
	
	List<Pauta> findAll()throws SistemaException;

	Pauta cadastrarNovaPauta(Pauta pauta)throws SistemaException;

	Pauta findById(String id)throws SistemaException;

	Pauta abrirSecaoVotacao(String id, Integer minutosEmAberto)throws SistemaException;

	Pauta receberVotoAssociado(String id, String idAssociado, String voto)throws SistemaException;

	Pauta emitirResultadoVotacao(String id)throws SistemaException;
}
