package br.com.product.exception.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.product.exception.ResourceException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handlerException(ResourceException e, WebRequest request) {
		return new ApiError.ApiErrorBuilder()
				.status(HttpStatus.NOT_FOUND.value())
				.message("Product not found")
				.timestamp(new Date()).build();

	}
	
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();

		List<ApiFieldError> apiFieldErrors = new ArrayList<ApiFieldError>();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			ApiFieldError builder = new ApiFieldError.ApiFieldErrorBuilder().field(fieldError.getField())
					.message(fieldError.getDefaultMessage()).build();
			apiFieldErrors.add(builder);
		}

		return new ApiError.ApiErrorBuilder().status(HttpStatus.BAD_REQUEST.value()).message("Validation failed")
				.timestamp(new Date()).fieldErrors(apiFieldErrors).build();
	}
	
	
//	@ExceptionHandler(ResourceError.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public ApiError handlerExceptionNotFound(ResourceError e, WebRequest request) {
//		return new ApiError.ApiErrorBuilder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Validation failed")
//				.timestamp(new Date()).build();
//
//	}
//	
	
}