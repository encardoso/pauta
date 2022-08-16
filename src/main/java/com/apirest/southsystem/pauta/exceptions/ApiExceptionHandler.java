package com.apirest.southsystem.pauta.exceptions;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.apirest.southsystem.pauta.util.DateTimeUtil;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardException> handleAnyException(Exception e, WebRequest request){		
		StandardException se = new StandardException(DateTimeUtil.getCurrentLocalDateTime(), e.getMessage(), "/",HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(se.getCodStatus()).body(se);
	}

	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<StandardException> handleNotFoundError(NotFoundException ex, final HttpServletRequest request) {
		StandardException se = new StandardException(DateTimeUtil.getCurrentLocalDateTime(), ex.getMessage(), request.getRequestURI(),HttpStatus.NOT_FOUND.value());       
        return ResponseEntity.status(se.getCodStatus()).body(se);
    }
  

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex.getBindingResult()
                .getFieldErrors().stream()
                	.map(fieldError -> fieldError.getDefaultMessage())
                	.collect(Collectors.toList());
        StandardException se = new StandardException(DateTimeUtil.getCurrentLocalDateTime(), errorList, "/",HttpStatus.BAD_REQUEST.value());        
        return ResponseEntity.status(se.getCodStatus()).body(se);
    }
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardException> entityNotFound(EntityNotFoundException e, HttpServletRequest request){		
		StandardException se = new StandardException(DateTimeUtil.getCurrentLocalDateTime(), e.getMessage(), request.getRequestURI(),HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(se.getCodStatus()).body(se);
	}

	@ExceptionHandler(SecaoVotacaoNaoIniciadaException.class)
	public ResponseEntity<StandardException> secaoVotacaoNaoIniciada(SecaoVotacaoNaoIniciadaException e, HttpServletRequest request){		
		StandardException se = new StandardException(DateTimeUtil.getCurrentLocalDateTime(), e.getMessage(), request.getRequestURI(), HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
		return ResponseEntity.status(se.getCodStatus()).body(se);
	}

	@ExceptionHandler(SecaoVotacaoExpiradaException.class)
	public ResponseEntity<StandardException> secaoVotacaoExpirada(SecaoVotacaoExpiradaException e, HttpServletRequest request){		
		StandardException se = new StandardException(DateTimeUtil.getCurrentLocalDateTime(), e.getMessage(), request.getRequestURI(), HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
		return ResponseEntity.status(se.getCodStatus()).body(se);
	}
}
