package com.apirest.southsystem.pauta.document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Pauta {

	@Id
	private String id;
	
	@NotBlank(message = "Descrição é requerido")
	@NotNull(message = "Descrição não poder ser nulo")
	@NotEmpty(message = "Descrição não poder ser vazio")
	private String descricao;
		
	private SecaoVotacao secaoVotacao;
	
}
