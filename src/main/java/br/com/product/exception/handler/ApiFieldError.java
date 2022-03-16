package br.com.product.exception.handler;

import lombok.Getter;

@Getter
public class ApiFieldError {

	private String field;
	private String message;

	static class ApiFieldErrorBuilder {

		private String field;
		private String message;

		public ApiFieldErrorBuilder field(String field) {
			this.field = field;
			return this;
		}

		public ApiFieldErrorBuilder message(String message) {
			this.message = message;
			return this;
		}

		public ApiFieldError build() {
			return new ApiFieldError(this);
		}
	}

	public ApiFieldError(ApiFieldErrorBuilder builder) {
		this.field = builder.field;
		this.message = builder.message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
