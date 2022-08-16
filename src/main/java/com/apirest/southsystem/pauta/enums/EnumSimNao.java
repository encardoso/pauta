package com.apirest.southsystem.pauta.enums;

public enum EnumSimNao {
	
	SIM("S","Sim"),NAO("N","Não")	
	;
	
	private String value;
	private String description;
	
	private EnumSimNao(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static EnumSimNao get(String value) {
		EnumSimNao retorno = null;
		for (int i = 0; i < values().length; i++) {
			EnumSimNao elemento = values()[i];
			if(elemento.getValue().equals(value)) {
				retorno = elemento;
			}
		}
		return retorno;
	}


}
