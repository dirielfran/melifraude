package com.meli.geolocalizacion.DTO;

import java.io.Serializable;


/**
 * The Class Estadistica.
 */
public class Estadistica implements  Serializable{

	
	/** The dist promedio. */
	private Double distPromedio;
	
	/** The dist minima. */
	private Double distMinima;
	
	/** The dist maxima. */
	private Double distMaxima;
	

	/**
	 * Gets the dist promedio.
	 *
	 * @return the dist promedio
	 */
	public Double getDistPromedio() {
		return distPromedio;
	}

	/**
	 * Sets the dist promedio.
	 *
	 * @param distPromedio the new dist promedio
	 */
	public void setDistPromedio(Double distPromedio) {
		this.distPromedio = distPromedio;
	}

	/**
	 * Gets the dist minima.
	 *
	 * @return the dist minima
	 */
	public Double getDistMinima() {
		return distMinima;
	}

	/**
	 * Sets the dist minima.
	 *
	 * @param distMinima the new dist minima
	 */
	public void setDistMinima(Double distMinima) {
		this.distMinima = distMinima;
	}

	/**
	 * Gets the dist maxima.
	 *
	 * @return the dist maxima
	 */
	public Double getDistMaxima() {
		return distMaxima;
	}

	/**
	 * Sets the dist maxima.
	 *
	 * @param distMaxima the new dist maxima
	 */
	public void setDistMaxima(Double distMaxima) {
		this.distMaxima = distMaxima;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Estadistica [distPromedio=" + distPromedio + ", distMinima=" + distMinima + ", distMaxima=" + distMaxima
				+ "]";
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5412493710745430248L;

}
