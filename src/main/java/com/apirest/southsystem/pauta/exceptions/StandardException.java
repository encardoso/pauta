package com.apirest.southsystem.pauta.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class StandardException implements Serializable{
	private static final long serialVersionUID = 8834162498048077004L;
	private LocalDateTime timeStamp;
	private Integer codStatus;
	private String message;
	private String path;	
	private List<String> errorList;
		
	public StandardException() {
		// TODO Auto-generated constructor stub
	}
	
	public StandardException(LocalDateTime timeStamp, List<String> errorList, String path,Integer codStatus) {
		super();
		this.timeStamp = timeStamp;		
		this.errorList = errorList;
		this.path = path;
		this.codStatus = codStatus;
	}
			
	public StandardException(LocalDateTime timeStamp, String message, String path,Integer codStatus) {
		super();
		this.timeStamp = timeStamp;		
		this.message = message;
		this.path = path;
		this.codStatus = codStatus;
	}	
}