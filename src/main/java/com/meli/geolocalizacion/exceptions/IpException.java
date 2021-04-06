package com.meli.geolocalizacion.exceptions;


/**
 * The Class IpException.
 */
public class IpException extends RuntimeException{


	/** The mensaje. */
	private String mensaje;
	
	/**
	 * Instantiates a new ip exception.
	 *
	 * @param mensaje the mensaje
	 */
	public IpException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return this.mensaje;
	}
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3072030978415006859L;

}
