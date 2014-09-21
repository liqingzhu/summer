package com.sx.util.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class SXException extends Exception {
	private int TPYE;
	public SXException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SXException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SXException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SXException(Throwable ex) {
		if(ex instanceof IOException){
//			type =0;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String msg = "";
		/*if(TYPE == 0){
			msg = "";
		}*/
		return msg;
	}
}
