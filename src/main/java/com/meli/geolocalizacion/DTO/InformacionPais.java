package com.meli.geolocalizacion.DTO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * The Class InformacionPais.
 */
public class InformacionPais implements Serializable{
	
	/** The name. */
	private String name;
	
	/** The languages. */
	private List<Map<String, String>> languages;
	
	/** The latlng. */
	private List<Double> latlng;
	
	/** The timezones. */
	private List<String> timezones;
	
	/** The currencies. */
	private List<Map<String, String>> currencies;


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the languages.
	 *
	 * @return the languages
	 */
	public List<Map<String, String>> getLanguages() {
		return languages;
	}
	
	/**
	 * Sets the languages.
	 *
	 * @param languages the languages
	 */
	public void setLanguages(List<Map<String, String>> languages) {
		this.languages = languages;
	}
	
	/**
	 * Gets the latlng.
	 *
	 * @return the latlng
	 */
	public List<Double> getLatlng() {
		return latlng;
	}
	
	/**
	 * Sets the latlng.
	 *
	 * @param latlng the new latlng
	 */
	public void setLatlng(List<Double> latlng) {
		this.latlng = latlng;
	}
	
	/**
	 * Gets the timezones.
	 *
	 * @return the timezones
	 */
	public List<String> getTimezones() {
		return timezones;
	}
	
	/**
	 * Sets the timezones.
	 *
	 * @param timezones the new timezones
	 */
	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}
	
	/**
	 * Gets the currencies.
	 *
	 * @return the currencies
	 */
	public List<Map<String, String>> getCurrencies() {
		return currencies;
	}
	
	/**
	 * Sets the currencies.
	 *
	 * @param currencies the currencies
	 */
	public void setCurrencies(List<Map<String, String>> currencies) {
		this.currencies = currencies;
	}



	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4955606283974087083L;
	
}
