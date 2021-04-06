package com.meli.geolocalizacion.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class DolarException.
 */
public class DolarException extends RuntimeException{


	/** The codigo error. */
	private int codigoError;
	
	/** The mensaje. */
	private String mensaje;
	
	/**
	 * Instantiates a new dolar exception.
	 *
	 * @param codigoError the codigo error
	 * @param mensaje the mensaje
	 */
	public DolarException(int codigoError, String mensaje) {
		super();
        this.codigoError=codigoError;
        this.mensaje = mensaje;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	@Override
	public String getMessage() {
		if(codigoError == 105 ) {
			this.mensaje = "Cotizacion del dolar no disponible";
		}
		
		return this.mensaje; 
	}
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8828147429961769020L;

}
