package com.irlan.algalog.domain.exception;

public class NegocioException extends RuntimeException{

	private static final long serialVersionUID = 2737469053601840851L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}
}
