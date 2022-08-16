package com.apirest.southsystem.pauta.document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class SecaoVotacao implements Serializable{
	
	private static final long serialVersionUID = 4447782868572152057L;
	
	private LocalDateTime inicio;
	
	private LocalDateTime fim;	
	
	private List<Voto> votos;
	
	private Long qtdeVotosSim;
	
	private Long qtdeVotosNao;
	
}