package com.training.mcg.domain;

/**
 * Created by An Nguyen on 9/17/2017.
 */
public class Error {

	/**
	 * The Error message.
	 */
	public String errorMessage;
	/**
	 * The Error type.
	 */
	public eError errorType;

	/**
	 * Instantiates a new Error.
	 *
	 * @param errorMessage the error message
	 * @param errorType    the error type
	 */
	public Error(String errorMessage, eError errorType) {
		this.errorMessage = errorMessage;
		this.errorType = errorType;
	}

	/**
	 * The enum E error.
	 */
	public enum eError {
		/**
		 * No data e error.
		 */
		NO_DATA,
		/**
		 * Backend error e error.
		 */
		BACKEND_ERROR
	}
}
