package com.meli.geolocalizacion.controller;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.geolocalizacion.DTO.Estadistica;
import com.meli.geolocalizacion.DTO.InformacionPais;
import com.meli.geolocalizacion.exceptions.DolarException;
import com.meli.geolocalizacion.exceptions.IpException;
import com.meli.geolocalizacion.interfaces.IEstadisticasServices;
import com.meli.geolocalizacion.interfaces.IPaisService;
import com.meli.geolocalizacion.utils.Utils;



/**
 * The Class IpsRestController.
 */
@RestController
@RequestMapping("/api")
public class IpsRestController {
	
	/** The pais service. */
	@Autowired
	private IPaisService paisService; 
	
	/** The estadisticas service. */
	@Autowired
	private IEstadisticasServices estadisticasService;
	
	/**
	 * informacionPais. Endpoint que retorna informacion de pais segun ip de consulta
	 *
	 * @param ip the ip. Ip de consulta
	 * @return the response entity
	 */
	@PostMapping("/geo/{ip}") 
	public ResponseEntity<?> informacionPais(@PathVariable String ip ){
		Map<String, Object> response = new HashMap<>();
		try {
			String ipformat = Utils.validate(ip);
			String cod = paisService.getCodPais(ipformat);
			InformacionPais paisInfoOrigen = paisService.getPaisInfo("ar");
			InformacionPais paisInfo = paisService.getPaisInfo(cod);
			String codigo = paisInfo.getCurrencies().get(0).get("code");
			Double distancia = paisService.getDistancia(paisInfoOrigen, paisInfo);
			estadisticasService.saveIps(ipformat, distancia, paisInfoOrigen.getName(), paisInfo.getName());
			response.put("horas", paisService.getHoras(paisInfo));
			response.put("pais", paisInfo);
			response.put("distancia", distancia);
			response.put("cotizacion", paisService.getCotizacionDolar(codigo , "USD"));
		}catch(IpException ipe) {
			response.put("mensaje", ipe.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}catch (DolarException de) {
			response.put("mensaje", de.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/geo/estadisticas")
	public ResponseEntity<?> getEstadisticas(){
		Map<String, Object> response = new HashMap<>();
		Estadistica estadisticas = new Estadistica();
		try {
			estadisticas = estadisticasService.getEstadisticas();
		} catch (DataAccessException  eSql) {
			response.put("mensaje", eSql.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Estadistica>(estadisticas, HttpStatus.OK);
	}
}
