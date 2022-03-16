package br.com.product.exception.handler;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


@Getter
public class ApiError {

	private Date timestamp;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private String error;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private String message;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private String path;

	private List<ApiFieldError> fieldErrors;

	public ApiError() {

	}

	public ApiError(ApiErrorBuilder builder) {
		this.timestamp = builder.timestamp;
		this.status = builder.status;
		this.error = builder.error;
		this.message = builder.message;
		this.path = builder.path;
		this.fieldErrors = builder.fieldErrors;
	}

	static class ApiErrorBuilder {
		private Date timestamp;
		private int status;
		private String error;
		private String message;
		private String path;
		private List<ApiFieldError> fieldErrors;

		public ApiErrorBuilder timestamp(Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ApiErrorBuilder status(int status) {
			this.status = status;
			return this;
		}

		public ApiErrorBuilder error(String error) {
			this.error = error;
			return this;
		}

		public ApiErrorBuilder message(String message) {
			this.message = message;
			return this;
		}

		public ApiErrorBuilder path(String path) {
			this.path = path;
			return this;
		}

		public ApiErrorBuilder fieldErrors(List<ApiFieldError> fieldErrors) {
			this.fieldErrors = fieldErrors;
			return this;
		}

		public ApiError build() {
			return new ApiError(this);
		}
	}

	public List<ApiFieldError> getFieldErrors() {
		return fieldErrors;
	}
}