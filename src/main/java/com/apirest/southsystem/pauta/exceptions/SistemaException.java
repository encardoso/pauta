package com.apirest.southsystem.pauta.exceptions;

import java.io.Serializable;

public class SistemaException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = -1726915619977092219L;

	public SistemaException(String message) {
		super(message);
	}
}
