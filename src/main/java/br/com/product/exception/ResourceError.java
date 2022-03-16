package br.com.product.exception;

public class ResourceError extends RuntimeException {

	
	private static final long serialVersionUID = 1L;


	public ResourceError() {
		super();
	}
	
	
	public ResourceError(String message) {
		super(message);
	}

}
