package com.apirest.southsystem.pauta.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.southsystem.pauta.document.Associado;
import com.apirest.southsystem.pauta.document.Pauta;
import com.apirest.southsystem.pauta.document.SecaoVotacao;
import com.apirest.southsystem.pauta.document.Voto;
import com.apirest.southsystem.pauta.enums.EnumSimNao;
import com.apirest.southsystem.pauta.exceptions.EntityNotFoundException;
import com.apirest.southsystem.pauta.exceptions.SecaoVotacaoExpiradaException;
import com.apirest.southsystem.pauta.exceptions.SecaoVotacaoNaoAbertaException;
import com.apirest.southsystem.pauta.exceptions.SecaoVotacaoNaoFechadaException;
import com.apirest.southsystem.pauta.exceptions.SecaoVotacaoNaoIniciadaException;
import com.apirest.southsystem.pauta.exceptions.SistemaException;
import com.apirest.southsystem.pauta.exceptions.VotoDuplicadoNaPautaException;
import com.apirest.southsystem.pauta.respository.PautaRepository;
import com.apirest.southsystem.pauta.util.DateTimeUtil;

@Service
public class PautaServiceImpl implements PautaService {

	@Autowired
	private PautaRepository repository;
	
	@Autowired
	private AssociadoService associadoService;	
	
	@Override
	public List<Pauta> findAll() throws SistemaException {		
		return repository.findAll();
	}

	@Override
	public Pauta cadastrarNovaPauta(Pauta pauta) throws SistemaException {
		return repository.save(pauta);
	}

	@Override
	public Pauta findById(String id) throws SistemaException {
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Id not found " + id));	
	}

	@Override
	public Pauta abrirSecaoVotacao(String id, Integer minutosEmAberto) throws SistemaException {
		Pauta pautaDb = this.findById(id);
		SecaoVotacao secaoVotacao = new SecaoVotacao();
		
		LocalDateTime now = DateTimeUtil.getCurrentLocalDateTime();		
		secaoVotacao.setInicio(now);
		if (minutosEmAberto == null || minutosEmAberto == 0) {
			secaoVotacao.setFim(secaoVotacao.getInicio().plusMinutes(1));
		}else {
			secaoVotacao.setFim(secaoVotacao.getInicio().plusMinutes(minutosEmAberto));
		}
		pautaDb.setSecaoVotacao(secaoVotacao);
		return repository.save(pautaDb);
	}

	@Override
	public Pauta receberVotoAssociado(String id, String idAssociado, String voto) throws SistemaException {
		Pauta pautaDb = this.findById(id);
				
		if (pautaDb.getSecaoVotacao().getVotos() == null || pautaDb.getSecaoVotacao().getVotos().isEmpty()) {
			pautaDb.getSecaoVotacao().setVotos(new ArrayList<>());
		}

		validarVoto(pautaDb, idAssociado);
		
		Associado associadoDb = associadoService.findById(idAssociado);
		
		Voto votoAssociado = new Voto();
		votoAssociado.setAssociado(associadoDb);
		votoAssociado.setSimNao(EnumSimNao.get(voto));
		
		pautaDb.getSecaoVotacao().getVotos().add(votoAssociado);
		return repository.save(pautaDb);
	}


	@Override
	public Pauta emitirResultadoVotacao(String id) throws SistemaException {
		Pauta pautaDb = this.findById(id);
		validarFechamentoSecaoVotacao(pautaDb);
		pautaDb.getSecaoVotacao().setQtdeVotosSim(contarVotos(pautaDb.getSecaoVotacao().getVotos(),EnumSimNao.SIM));
		pautaDb.getSecaoVotacao().setQtdeVotosNao(contarVotos(pautaDb.getSecaoVotacao().getVotos(),EnumSimNao.NAO));
		return repository.save(pautaDb);
	}

	private void validarVoto(Pauta pautaDb, String idAssociado)
			throws SecaoVotacaoNaoIniciadaException, SecaoVotacaoExpiradaException {
		LocalDateTime now = DateTimeUtil.getCurrentLocalDateTime();
		if(now.isBefore(pautaDb.getSecaoVotacao().getInicio())){
			throw new SecaoVotacaoNaoIniciadaException("Seção de votação ainda não iniciou");
		}
		
		if(now.isAfter(pautaDb.getSecaoVotacao().getFim())){
			throw new SecaoVotacaoExpiradaException("Seção de votação acabou");
		}
		
		if (pautaDb.getSecaoVotacao().getVotos().size() > 0) {
			long qtdeVotosDuplicados = pautaDb.getSecaoVotacao().getVotos().stream()
					.filter(
							(Voto v) -> v.getAssociado().getId().equals(idAssociado)
					).count();
			if (qtdeVotosDuplicados  > 0) {
				throw new VotoDuplicadoNaPautaException("Voto já computado nessa Pauta");
			} 
		}
	}
	
	private void validarFechamentoSecaoVotacao(Pauta pautaDb) throws SecaoVotacaoNaoFechadaException{
		LocalDateTime now = DateTimeUtil.getCurrentLocalDateTime();
		if(pautaDb.getSecaoVotacao() == null || (pautaDb.getSecaoVotacao().getInicio() == null)) {
			throw new SecaoVotacaoNaoAbertaException("Seção de votação ainda não foi aberta para esta pauta");
		}
		
		if(now.isBefore(pautaDb.getSecaoVotacao().getFim())){
			throw new SecaoVotacaoNaoFechadaException("Seção de votação ainda está em andamento");
		}
	}
	
	private long contarVotos(List<Voto> votos, EnumSimNao enumSimNao) {
		long result = 0;
		result = votos.stream()
			.filter((Voto voto) -> voto.getSimNao().equals(enumSimNao)).count();
		return result;
	}
}
