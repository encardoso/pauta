package com.apirest.southsystem.pauta.document;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.apirest.southsystem.pauta.enums.EnumSimNao;

import lombok.Data;

@Data
public class Voto implements Serializable {
	
	private static final long serialVersionUID = -592057293767754740L;
	
	private EnumSimNao simNao;

	@DBRef
	private Associado associado;
	

}