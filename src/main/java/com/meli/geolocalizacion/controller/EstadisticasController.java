package com.meli.geolocalizacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.meli.geolocalizacion.interfaces.IEstadisticasServices;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadisticasController.
 */
@Controller
public class EstadisticasController {
	
	/** The estadisticas service. */
	@Autowired
	private IEstadisticasServices estadisticasService;
	
	
	/**
	 * Estadisticas. Metodo que retorna a la  vista por medio de objeto model las 
	 * estadisticas 
	 *
	 * @param modelo the modelo
	 * @return the string
	 */
	@GetMapping("/estadisticas")
	public String estadisticas(Model modelo) {
		try {
			modelo.addAttribute("estadisticas", estadisticasService.getEstadisticas());
		} catch (Exception e) {
			modelo.addAttribute("mensaje", e.getMessage());
		}
		return "estadisticas";
	}

}
