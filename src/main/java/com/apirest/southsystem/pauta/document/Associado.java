package com.apirest.southsystem.pauta.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apirest.southsystem.pauta.util.DateTimeUtil;

import lombok.Data;

@Data
@Document
public class Associado {

	@Id
	private String id;
	private String cpf;
	private String nome;
	private LocalDateTime dataCadastro;
	

	public Associado() {
		// TODO Auto-generated constructor stub
	}
	
	public Associado(String id, String cpf, String nome) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataCadastro = DateTimeUtil.getCurrentLocalDateTime();
	
	}
	
	public Associado(String id, String cpf, String nome, LocalDateTime dataCadastro) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
	}
}